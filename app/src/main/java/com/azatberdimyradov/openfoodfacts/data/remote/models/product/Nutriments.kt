package com.azatberdimyradov.openfoodfacts.data.remote.models.product

import com.google.gson.annotations.SerializedName

data class Nutriments(
    val carbohydrates: Double,
    val carbohydrates_100g: Double,
    val carbohydrates_unit: String,
    val carbohydrates_value: Double,
    val energy: Double,
    @SerializedName("energy-kcal")
    val energy_kcal: Double,
    @SerializedName("energy-kcal_100g")
    val energy_kcal_100g: Double,
    @SerializedName("energy-kcal_unit")
    val energy_kcal_unit: String,
    @SerializedName("energy-kcal_value")
    val energy_kcal_value: Double,
    val energy_100g: Double,
    val energy_unit: String,
    val energy_value: Double,
    val fat: Double,
    @SerializedName("fat_100g")
    val fat_100g: Double,
    @SerializedName("fat_unit")
    val fat_unit: String,
    @SerializedName("fat_value")
    val fat_value: Double,
    val fiber: Double,
    val fiber_100g: Double,
    val fiber_unit: String,
    val fiber_value: Double,
    @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_100g")
    val fruits_vegetables_nuts_estimate_from_ingredients_100g: Double,
    @SerializedName("nova-group")
    val nova_group: Double,
    @SerializedName("nova-group_100g")
    val nova_group_100g: Double,
    @SerializedName("nova-group_serving")
    val nova_group_serving: Double,
    @SerializedName("nutrition-score-fr")
    val nutrition_score_fr: Double,
    @SerializedName("nutrition-score-fr_100g")
    val nutrition_score_fr_100g: Double,
    @SerializedName("pantothenic-acid")
    val pantothenic_acid: Double,
    @SerializedName("pantothenic-acid_100g")
    val pantothenic_acid_100g: Double,
    @SerializedName("pantothenic-acid_label")
    val pantothenic_acid_label: String,
    @SerializedName("pantothenic-acid_unit")
    val pantothenic_acid_unit: String,
    @SerializedName("pantothenic-acid_value")
    val pantothenic_acid_value: Double,
    val proteins: Double,
    val proteins_100g: Double,
    val proteins_unit: String,
    val proteins_value: Double,
    val salt: Double,
    val salt_100g: Double,
    val salt_unit: String,
    val salt_value: Double,
    @SerializedName("saturated-fat")
    val saturated_fat: Double,
    @SerializedName("saturated-fat_100g")
    val saturated_fat_100g: Double,
    @SerializedName("saturated-fat_unit")
    val saturated_fat_unit: String,
    @SerializedName("saturated-fat_value")
    val saturated_fat_value: Double,
    val sodium: Double,
    val sodium_100g: Double,
    val sodium_unit: String,
    val sodium_value: Double,
    val sugars: Double,
    val sugars_100g: Double,
    val sugars_unit: String,
    val sugars_value: Double,
    @SerializedName("vitamin-b12")
    val vitamin_b12: Any,
    @SerializedName("vitamin-b12_100g")
    val vitamin_b12_100g: Any,
    @SerializedName("vitamin-b12_label")
    val vitamin_b12_label: String,
    @SerializedName("vitamin-b12_unit")
    val vitamin_b12_unit: String,
    @SerializedName("vitamin-b12_value")
    val vitamin_b12_value: Double,
    @SerializedName("vitamin-b6")
    val vitamin_b6: Double,
    @SerializedName("vitamin-b6_100g")
    val vitamin_b6_100g: Double,
    @SerializedName("vitamin-b6_label")
    val vitamin_b6_label: String,
    @SerializedName("vitamin-b6_unit")
    val vitamin_b6_unit: String,
    @SerializedName("vitamin-b6_value")
    val vitamin_b6_value: Double,
    @SerializedName("vitamin-pp")
    val vitamin_pp: Double,
    @SerializedName("vitamin-pp_100g")
    val vitamin_pp_100g: Double,
    @SerializedName("vitamin-pp_label")
    val vitamin_pp_label: String,
    @SerializedName("vitamin-pp_unit")
    val vitamin_pp_unit: String,
    @SerializedName("vitamin-pp_value")
    val vitamin_pp_value: Double
)