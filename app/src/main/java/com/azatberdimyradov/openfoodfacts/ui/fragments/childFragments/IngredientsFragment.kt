package com.azatberdimyradov.openfoodfacts.ui.fragments.childFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.databinding.FragmentIngredientsBinding
import com.azatberdimyradov.openfoodfacts.ui.OpenFoodFactsViewModel
import com.azatberdimyradov.openfoodfacts.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IngredientsFragment: Fragment(R.layout.fragment_ingredients) {

    private val binding by viewBinding(FragmentIngredientsBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserves()
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result){
                        is Resource.Success -> {
                            result.data?.let {
                                fillProductIngrediens(it)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message ?: "Error", requireView())
                        else -> { }
                    }
                }
            }
        }
    }

    private fun fillProductIngrediens(product: ProductResponse) {
        binding.apply {
            ivNova.setNovaImage(product.product.nova_group)
            tvIngredientsList.text = setIngredientList(product, this@IngredientsFragment)
            tvAdditives.text = setAdditivesText(product, this@IngredientsFragment)
            tvVitamins.text = setVitamins(product, this@IngredientsFragment)
            tvGroupInfo.text = setNovaGroupInfo(product,this@IngredientsFragment)
        }
    }

}