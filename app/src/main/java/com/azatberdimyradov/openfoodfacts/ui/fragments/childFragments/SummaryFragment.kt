package com.azatberdimyradov.openfoodfacts.ui.fragments.childFragments

import android.os.Bundle
import android.view.View
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.adapters.NutrientLevelAdapter
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.databinding.FragmentSummaryBinding
import com.azatberdimyradov.openfoodfacts.ui.OpenFoodFactsViewModel
import com.azatberdimyradov.openfoodfacts.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SummaryFragment: Fragment(R.layout.fragment_summary) {

    private val binding by viewBinding(FragmentSummaryBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by activityViewModels()
    private lateinit var nutrientLevelAdapter: NutrientLevelAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeToObserves()
    }

    private fun initRecyclerView() {
        nutrientLevelAdapter = NutrientLevelAdapter()
        binding.rvNutrientLevel.apply {
            adapter = nutrientLevelAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.productResponse.collect { result ->
                    when (result){
                        is Resource.Success -> {
                            result.data?.let {
                                fillProductSummary(it)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message?: "Error", requireView())
                        else -> {}
                    }
                }
            }
        }
    }

    private fun fillProductSummary(product: ProductResponse) {
        binding.apply {
            ivNutriscore.setNutriscoreImage(product.product.nutriscore_grade)
            ivNova.setNovaImage(product.product.nova_group)
            ivEcoscore.setEcoscoreImage(product.product.ecoscore_grade)
            tvCategories.text = setCategories(product, this@SummaryFragment)
            tvAdditives.text = setAdditivesText(product, this@SummaryFragment)
            nutrientLevelAdapter.submitList(product.product.convertNutrientLevelList())
        }
    }
}