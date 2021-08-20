package com.azatberdimyradov.openfoodfacts.utils

import android.view.View
import android.widget.ImageView
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_A
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_B
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_C
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_D
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_E
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_1
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_2
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_3
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_4
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_A
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_B
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_C
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_D
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_E
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

fun ImageView.setNutriscoreImage(nutriscore: String) {
    when (nutriscore) {
        NUTRISCORE_A -> this.setBackgroundResource(R.drawable.ic_nutriscore_a)
        NUTRISCORE_B -> this.setBackgroundResource(R.drawable.ic_nutriscore_b)
        NUTRISCORE_C -> this.setBackgroundResource(R.drawable.ic_nutriscore_c)
        NUTRISCORE_D -> this.setBackgroundResource(R.drawable.ic_nutriscore_d)
        NUTRISCORE_E -> this.setBackgroundResource(R.drawable.ic_nutriscore_e)
        else -> this.setBackgroundResource(R.drawable.ic_nutriscore_unknown)
    }
}

fun ImageView.setNovaImage(nova: Int) {
    when (nova) {
        NOVA_1 -> this.setBackgroundResource(R.drawable.ic_nova_group_1)
        NOVA_2 -> this.setBackgroundResource(R.drawable.ic_nova_group_2)
        NOVA_3 -> this.setBackgroundResource(R.drawable.ic_nova_group_3)
        NOVA_4 -> this.setBackgroundResource(R.drawable.ic_nova_group_4)
        else -> this.setBackgroundResource(R.drawable.ic_nova_group_unknown)
    }
}

fun ImageView.setEcoscoreImage(ecoscore: String) {
    when (ecoscore) {
        ECOSCORE_A -> this.setBackgroundResource(R.drawable.ic_ecoscore_a)
        ECOSCORE_B -> this.setBackgroundResource(R.drawable.ic_ecoscore_b)
        ECOSCORE_C -> this.setBackgroundResource(R.drawable.ic_ecoscore_c)
        ECOSCORE_D -> this.setBackgroundResource(R.drawable.ic_ecoscore_d)
        ECOSCORE_E -> this.setBackgroundResource(R.drawable.ic_ecoscore_e)
        else -> this.setBackgroundResource(R.drawable.ic_ecoscore_unknown)
    }
}