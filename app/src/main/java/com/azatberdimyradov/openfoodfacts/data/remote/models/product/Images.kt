package com.azatberdimyradov.openfoodfacts.data.remote.models.product

data class Images(
    val front: Front,
    val front_en: FrontEn,
    val ingredients: Ingredients,
    val ingredients_en: IngredientsEn,
    val ingredients_fr: IngredientsFr,
    val nutrition: Nutrition,
    val nutrition_en: NutritionEn,
    val nutrition_fr: NutritionFr
)