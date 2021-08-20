package com.azatberdimyradov.openfoodfacts.ui.fragments

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.databinding.FragmentScanBinding
import com.azatberdimyradov.openfoodfacts.ui.OpenFoodFactsViewModel
import com.azatberdimyradov.openfoodfacts.utils.CaptureAct
import com.azatberdimyradov.openfoodfacts.utils.Constants.SUCCESS_TIME_DELAY
import com.azatberdimyradov.openfoodfacts.utils.Resource
import com.azatberdimyradov.openfoodfacts.utils.convertToProductItem
import com.azatberdimyradov.openfoodfacts.utils.showSnackBar
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ScanFragment @Inject constructor(

) : Fragment(R.layout.fragment_scan) {

    private val binding by viewBinding(FragmentScanBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by viewModels()
    private lateinit var successAnimation: AnimatedVectorDrawable
    private var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObserves()

        binding.apply {
            imSuccess.apply {
                val d = drawable
                if (d is AnimatedVectorDrawable) {
                    successAnimation = d
                }
            }
            btFind.setOnClickListener {
                val input = tilBarcode.editText?.text.toString()
                if (input.isNotEmpty()) {
                    viewModel.getProductByBarcode(input)
                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                onSuccess(it)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message ?: "Error", requireView())
                        is Resource.Loading -> onLoading()
                        is Resource.Empty -> {
                        }
                    }
                }
            }
        }
    }

    private fun onSuccess(productResponse: ProductResponse) {
        binding.progressBar.isVisible = false
        binding.imSuccess.isVisible = true
        successAnimation.start()
        viewModel.insertProductItemIntoDb(
            productResponse.convertToProductItem()
        )
        job?.cancel()
        job = MainScope().launch {
            delay(SUCCESS_TIME_DELAY)
            val action =
                ScanFragmentDirections.actionScanFragmentToProductDetailsFragment(productResponse.product.code)
            findNavController().navigate(action)
        }
    }

    private fun onLoading() {
        binding.linearLayout.isVisible = false
        binding.progressBar.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.scan_fragment_menu, menu)
        val scanItem = menu.findItem(R.id.action_scan)
        scanItem.setOnMenuItemClickListener {
            scanCode()
            true
        }
    }

    private fun scanCode() {
        val integrator = IntentIntegrator(requireActivity())
        integrator.captureActivity = CaptureAct::class.java
        integrator.setOrientationLocked(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES)
        integrator.setPrompt("Scanning Code")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                binding.tilBarcode.editText?.setText(result.contents)
                viewModel.getProductByBarcode(result.contents)
            } else {
                showSnackBar("No Result", requireView())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}