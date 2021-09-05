package com.azatberdimyradov.openfoodfacts.ui.fragments.childFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.adapters.NutrientLevelAdapter
import com.azatberdimyradov.openfoodfacts.data.adapters.NutritionFactsAdapter
import com.azatberdimyradov.openfoodfacts.data.adapters.NutritionHeaderAdapter
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.databinding.FragmentNutritionBinding
import com.azatberdimyradov.openfoodfacts.ui.OpenFoodFactsViewModel
import com.azatberdimyradov.openfoodfacts.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NutritionFragment : Fragment(R.layout.fragment_nutrition) {

    private val binding by viewBinding(FragmentNutritionBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by activityViewModels()
    private lateinit var nutritionFactsAdapter: NutritionFactsAdapter
    private lateinit var nutritionHeaderAdapter: NutritionHeaderAdapter
    private lateinit var nutrientLevelAdapter: NutrientLevelAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeToObserves()
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.productResponse.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                fillProductNutrition(it)
                            }
                        }
                        is Resource.Error -> showSnackBar(result.message ?: "Error", requireView())
                        else -> {
                        }
                    }
                }
            }
        }
    }

    private fun fillProductNutrition(product: ProductResponse) {
        binding.apply {
            ivEcoscore.setEcoscoreImage(product.product.ecoscore_grade)
            nutrientLevelAdapter.submitList(product.product.convertNutrientLevelList())
            nutritionFactsAdapter.submitList(product.product.nutriments.convertNutritionFactItemList())
        }
    }

    private fun initRecyclerView() {
        nutritionFactsAdapter = NutritionFactsAdapter()
        nutritionHeaderAdapter = NutritionHeaderAdapter()
        nutrientLevelAdapter = NutrientLevelAdapter()
        val concatAdapter = ConcatAdapter(nutritionHeaderAdapter, nutritionFactsAdapter)
        binding.apply {
            rvNutrientLevel.apply {
                adapter = nutrientLevelAdapter
                layoutManager = LinearLayoutManager(requireContext())
                hasFixedSize()
            }
            rvNutritionFact.apply {
                adapter = concatAdapter
                layoutManager = LinearLayoutManager(requireContext())
                hasFixedSize()
            }
        }
    }
}