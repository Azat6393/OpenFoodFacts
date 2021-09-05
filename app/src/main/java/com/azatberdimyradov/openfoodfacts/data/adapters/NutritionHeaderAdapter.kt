package com.azatberdimyradov.openfoodfacts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.openfoodfacts.databinding.HeaderNutritionFactsBinding

class NutritionHeaderAdapter :
    RecyclerView.Adapter<NutritionHeaderAdapter.NutritionHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionHeaderViewHolder {
        return NutritionHeaderViewHolder(
            HeaderNutritionFactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NutritionHeaderViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class NutritionHeaderViewHolder(private val binding: HeaderNutritionFactsBinding) :
        RecyclerView.ViewHolder(binding.root)

}