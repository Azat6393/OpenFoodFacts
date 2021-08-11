package com.azatberdimyradov.openfoodfacts.ui

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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.databinding.FragmentScanBinding
import com.azatberdimyradov.openfoodfacts.utils.CaptureAct
import com.azatberdimyradov.openfoodfacts.utils.Resource
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScanFragment : Fragment(R.layout.fragment_scan) {

    private val binding by viewBinding(FragmentScanBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by viewModels()
    private lateinit var successAnimation: AnimatedVectorDrawable

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
                if (input.isNotEmpty()){
                    viewModel.getProductByBarcode(input)
                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun subscribeToObserves(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result){
                        is Resource.Success -> onSuccess()
                        is Resource.Error -> showSnackBar(result.message)
                        is Resource.Loading -> onLoading()
                        is Resource.Empty -> { }
                    }
                }
            }
        }
    }

    private fun onSuccess() {
        binding.progressBar.isVisible = false
        binding.imSuccess.isVisible = true
        successAnimation.start()
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
                showSnackBar("No Result")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun showSnackBar(message: String?){
        Snackbar.make(
            requireView(),message ?: "Error", Snackbar.LENGTH_SHORT
        ).show()
    }
}