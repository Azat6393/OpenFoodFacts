package com.azatberdimyradov.openfoodfacts.data.remote.models.product

data class Nutrition(
    val geometry: String,
    val imgid: String,
    val normalize: String,
    val ocr: Int,
    val orientation: String,
    val rev: String,
    val white_magic: Any
)