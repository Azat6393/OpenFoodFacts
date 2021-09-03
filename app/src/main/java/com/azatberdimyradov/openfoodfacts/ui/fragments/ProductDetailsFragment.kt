package com.azatberdimyradov.openfoodfacts.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import androidx.viewpager2.widget.ViewPager2
import com.azatberdimyradov.openfoodfacts.ui.MainActivity
import com.azatberdimyradov.openfoodfacts.ui.OpenFoodFactsViewModel
import com.azatberdimyradov.openfoodfacts.ui.fragments.childFragments.FragmentAdapter
import com.bumptech.glide.RequestManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_details.*
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).binding.bottomNavigationView.isVisible = false
        setupChildFragments()
        subscribeToObserves()
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                fillDetails(it)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message ?: "Error", requireView())
                        else -> { }
                    }
                }
            }
        }
    }

    private fun setupChildFragments() {
        val fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)

        binding.apply {
            viewPager.adapter = fragmentAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Summary"
                    1 -> tab.text = "Ingredients"
                    2 -> tab.text = "Nutrition"
                }
            }.attach()
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