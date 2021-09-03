package com.azatberdimyradov.openfoodfacts.data.remote.models.product

import com.google.gson.annotations.SerializedName

data class Nutriments(
    val carbohydrates: Int,
    val carbohydrates_100g: Int,
    val carbohydrates_unit: String,
    val carbohydrates_value: Int,
    val energy: Int,
    @SerializedName("energy-kcal")
    val energy_kcal: Int,
    @SerializedName("energy-kcal_100g")
    val energy_kcal_100g: Int,
    @SerializedName("energy-kcal_unit")
    val energy_kcal_unit: String,
    @SerializedName("energy-kcal_value")
    val energy_kcal_value: Int,
    val energy_100g: Int,
    val energy_unit: String,
    val energy_value: Int,
    val fat: Int,
    @SerializedName("fat_100g")
    val fat_100g: Int,
    @SerializedName("fat_unit")
    val fat_unit: String,
    @SerializedName("fat_value")
    val fat_value: Int,
    val fiber: Int,
    val fiber_100g: Int,
    val fiber_unit: String,
    val fiber_value: Int,
    @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_100g")
    val fruits_vegetables_nuts_estimate_from_ingredients_100g: Int,
    @SerializedName("nova-group")
    val nova_group: Int,
    @SerializedName("nova-group_100g")
    val nova_group_100g: Int,
    @SerializedName("nova-group_serving")
    val nova_group_serving: Int,
    @SerializedName("nutrition-score-fr")
    val nutrition_score_fr: Int,
    @SerializedName("nutrition-score-fr_100g")
    val nutrition_score_fr_100g: Int,
    @SerializedName("pantothenic-acid")
    val pantothenic_acid: Double,
    @SerializedName("pantothenic-acid_100g")
    val pantothenic_acid_100g: Double,
    @SerializedName("pantothenic-acid_label")
    val pantothenic_acid_label: String,
    @SerializedName("pantothenic-acid_unit")
    val pantothenic_acid_unit: String,
    @SerializedName("pantothenic-acid_value")
    val pantothenic_acid_value: Int,
    val proteins: Int,
    val proteins_100g: Int,
    val proteins_unit: String,
    val proteins_value: Int,
    val salt: Double,
    val salt_100g: Double,
    val salt_unit: String,
    val salt_value: Double,
    @SerializedName("saturated-fat")
    val saturated_fat: Int,
    @SerializedName("saturated-fat_100g")
    val saturated_fat_100g: Int,
    @SerializedName("saturated-fat_unit")
    val saturated_fat_unit: String,
    @SerializedName("saturated-fat_value")
    val saturated_fat_value: Int,
    val sodium: Double,
    val sodium_100g: Double,
    val sodium_unit: String,
    val sodium_value: Double,
    val sugars: Int,
    val sugars_100g: Int,
    val sugars_unit: String,
    val sugars_value: Int,
    @SerializedName("vitamin-b12")
    val vitamin_b12: Any,
    @SerializedName("vitamin-b12_100g")
    val vitamin_b12_100g: Any,
    @SerializedName("vitamin-b12_label")
    val vitamin_b12_label: String,
    @SerializedName("vitamin-b12_unit")
    val vitamin_b12_unit: String,
    @SerializedName("vitamin-b12_value")
    val vitamin_b12_value: Int,
    @SerializedName("vitamin-b6")
    val vitamin_b6: Double,
    @SerializedName("vitamin-b6_100g")
    val vitamin_b6_100g: Double,
    @SerializedName("vitamin-b6_label")
    val vitamin_b6_label: String,
    @SerializedName("vitamin-b6_unit")
    val vitamin_b6_unit: String,
    @SerializedName("vitamin-b6_value")
    val vitamin_b6_value: Int,
    @SerializedName("vitamin-pp")
    val vitamin_pp: Double,
    @SerializedName("vitamin-pp_100g")
    val vitamin_pp_100g: Double,
    @SerializedName("vitamin-pp_label")
    val vitamin_pp_label: String,
    @SerializedName("vitamin-pp_unit")
    val vitamin_pp_unit: String,
    @SerializedName("vitamin-pp_value")
    val vitamin_pp_value: Int
)