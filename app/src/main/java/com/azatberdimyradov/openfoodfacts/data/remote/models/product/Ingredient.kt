package com.azatberdimyradov.openfoodfacts.data.remote.models

data class Ingredient(
    val has_sub_ingredients: String,
    val id: String,
    val percent: String,
    val percent_estimate: Any,
    val percent_max: Any,
    val percent_min: Any,
    val rank: Int,
    val text: String,
    val vegan: String,
    val vegetarian: String
)