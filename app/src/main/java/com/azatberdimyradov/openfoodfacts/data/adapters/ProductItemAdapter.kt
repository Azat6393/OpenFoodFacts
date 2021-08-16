package com.azatberdimyradov.openfoodfacts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.databinding.ItemProductBinding
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_A
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_B
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_C
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_D
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_E
import com.azatberdimyradov.openfoodfacts.utils.setNutriscoreImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ProductItemAdapter(val listener: OnItemClick) : ListAdapter<ProductItem, ProductItemAdapter.ProductItemViewHolder>(
    DiffCallBack
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ProductItemViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val productItem = getItem(position)
                    if (productItem != null){
                        listener.onItemClickListener(productItem)
                    }
                }
            }
        }

        fun bind(productItem: ProductItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(productItem.imageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_image_24)
                    .into(ivProductImage)
                tvProductName.text = productItem.productName
                tvBrandNameAndMl.text = "${productItem.brandName} - ${productItem.quantity}"
                tvAddedDate.text = productItem.addedDateFormatted
                tvBarcode.text = productItem.barcode
                ivNutriscore.setNutriscoreImage(productItem.nutriscore)
            }
        }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClick{
        fun onItemClickListener(productItem: ProductItem)
    }
}