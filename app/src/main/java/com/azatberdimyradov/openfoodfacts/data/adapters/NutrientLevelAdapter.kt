package com.azatberdimyradov.openfoodfacts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.ListAdapter
import com.azatberdimyradov.openfoodfacts.utils.getDecRes
import com.azatberdimyradov.openfoodfacts.utils.getImgRes

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.openfoodfacts.data.remote.models.NutrientLevelItem
import com.azatberdimyradov.openfoodfacts.databinding.ItemNutrientLvlBinding

class NutrientLevelAdapter :
    ListAdapter<NutrientLevelItem, NutrientLevelAdapter.NutrientLevelViewHolder>(
        DiffCallBack
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientLevelViewHolder {
        return NutrientLevelViewHolder(
            ItemNutrientLvlBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NutrientLevelViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    inner class NutrientLevelViewHolder(private val binding: ItemNutrientLvlBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: NutrientLevelItem){
                binding.ivNutrient.setBackgroundResource(item.getImgRes())
                binding.tvNutrient.text = buildSpannedString {
                    append("${item.value} ${item.value_unit} ${item.name}")
                    append("\n")
                    append(itemView.context.getString(item.getDecRes()))
                }
            }

    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<NutrientLevelItem>() {
            override fun areItemsTheSame(
                oldItem: NutrientLevelItem,
                newItem: NutrientLevelItem
            ): Boolean {
                return oldItem.name == oldItem.name
            }

            override fun areContentsTheSame(
                oldItem: NutrientLevelItem,
                newItem: NutrientLevelItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}