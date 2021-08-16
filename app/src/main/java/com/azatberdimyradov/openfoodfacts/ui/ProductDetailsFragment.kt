package com.azatberdimyradov.openfoodfacts.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.databinding.FragmentProductDetailsBinding
import com.azatberdimyradov.openfoodfacts.utils.Resource
import com.azatberdimyradov.openfoodfacts.utils.showSnackBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by viewModels()
    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserves()
        viewModel.getProductByBarcode(args.productBarcode)
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                fillDetails(it)
                                loadingState(false)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message ?: "Error", requireView())
                        is Resource.Loading -> loadingState(true)
                        is Resource.Empty -> {

                        }
                    }
                }
            }
        }
    }

    private fun loadingState(state: Boolean) {
        binding.apply {
            headerCardView.isVisible = !state
            progressBar.isVisible = state
        }
    }

    private fun fillDetails(productResponse: ProductResponse) {
        binding.apply {
            tvProductName.text = productResponse.product.product_name
            tvBrandName.text = productResponse.product.brands
            tvProductMl.text = productResponse.product.quantity
            glide.load(productResponse.product.image_front_url)
                .into(imProductImage)
        }
    }
}