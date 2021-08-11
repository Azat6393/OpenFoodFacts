package com.azatberdimyradov.openfoodfacts.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.adapters.ProductItemAdapter
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.databinding.FragmentHistoryBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history), ProductItemAdapter.OnItemClick {

    private val binding by viewBinding(FragmentHistoryBinding::bind)
    private val viewModel: OpenFoodFactsViewModel by viewModels()
    private lateinit var productItemAdapter: ProductItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        subscribeToObserves()
    }

    private fun setupRecyclerView() {
        productItemAdapter = ProductItemAdapter(this)
        binding.rvProductItems.apply {
            adapter = productItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.rvProductItems)
        }
    }

    private fun subscribeToObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.observeProductItems.collect { result ->
                    productItemAdapter.submitList(result)
                }
            }
        }
    }

    val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val productItem = productItemAdapter.currentList[position]
            viewModel.deleteProductItemFromDb(productItem)
            Snackbar.make(requireView(), "Successfully deleted", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.insertProductItemIntoDb(productItem)
                }
                show()
            }
        }
    }

    override fun onItemClickListener(productItem: ProductItem) {

    }
}