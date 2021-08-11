package com.azatberdimyradov.openfoodfacts.data.remote.models

data class EcoscoreData(
    val adjustments: Adjustments,
    val ecoscore_not_applicable_for_category: String,
    val status: String
)