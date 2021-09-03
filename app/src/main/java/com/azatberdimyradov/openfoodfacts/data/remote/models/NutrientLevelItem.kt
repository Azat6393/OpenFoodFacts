package com.azatberdimyradov.openfoodfacts.data.remote.models

data class NutrientLevelItem(
    val name: String,
    val category: String,
    val value: Double,
    val value_unit: String
)
