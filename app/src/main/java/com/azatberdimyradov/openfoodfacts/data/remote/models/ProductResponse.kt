package com.azatberdimyradov.openfoodfacts.data.remote.models

import com.azatberdimyradov.openfoodfacts.data.remote.models.product.Product

data class FoodFactsResponse(
    val code: String,
    val product: Product,
    val status: Int,
    val status_verbose: String
)