package com.azatberdimyradov.openfoodfacts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.openfoodfacts.data.remote.models.NutritionFactsItem
import com.azatberdimyradov.openfoodfacts.databinding.ItemNutritionFactsBinding

class NutritionFactsAdapter :
    ListAdapter<NutritionFactsItem, NutritionFactsAdapter.NutritionFactsViewHolder>(
        DiffCallBack
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionFactsViewHolder {
        return NutritionFactsViewHolder(
            ItemNutritionFactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NutritionFactsViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    inner class NutritionFactsViewHolder(private val binding: ItemNutritionFactsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NutritionFactsItem) {
            binding.apply {
                tvName.text = item.name
                tvValue.text = "${item.value} ${item.value_unit}"
            }
        }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<NutritionFactsItem>() {
            override fun areItemsTheSame(
                oldItem: NutritionFactsItem,
                newItem: NutritionFactsItem
            ): Boolean {
                return newItem.name == oldItem.name
            }

            override fun areContentsTheSame(
                oldItem: NutritionFactsItem,
                newItem: NutritionFactsItem
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}