package com.azatberdimyradov.openfoodfacts.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.google.android.material.snackbar.Snackbar


fun ProductResponse.convertToProductItem(): ProductItem {
    return ProductItem(
        productName = this.product.product_name,
        brandName = this.product.brands,
        quantity = this.product.quantity,
        barcode = this.product.code,
        imageUrl = this.product.image_front_url,
        nutriscore = this.product.nutriscore_grade,
    )
}

fun showSnackBar(message: String?, view: View) {
    Snackbar.make(
        view, message ?: "Error", Snackbar.LENGTH_SHORT
    ).show()
}

fun ImageView.setNutriscoreImage(nutriscore: String){
    when (nutriscore) {
        Constants.NUTRISCORE_A -> this.setBackgroundResource(R.drawable.ic_nutriscore_a)
        Constants.NUTRISCORE_B -> this.setBackgroundResource(R.drawable.ic_nutriscore_b)
        Constants.NUTRISCORE_C -> this.setBackgroundResource(R.drawable.ic_nutriscore_c)
        Constants.NUTRISCORE_D -> this.setBackgroundResource(R.drawable.ic_nutriscore_d)
        Constants.NUTRISCORE_E -> this.setBackgroundResource(R.drawable.ic_nutriscore_e)
        else -> this.setBackgroundResource(R.drawable.ic_nutriscore_unknown)
    }
}