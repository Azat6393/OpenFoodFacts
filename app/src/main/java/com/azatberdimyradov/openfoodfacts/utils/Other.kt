package com.azatberdimyradov.openfoodfacts.utils

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.NutrientLevelItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.NutritionFactsItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.data.remote.models.product.NutrientLevels
import com.azatberdimyradov.openfoodfacts.data.remote.models.product.Nutriments
import com.azatberdimyradov.openfoodfacts.data.remote.models.product.Product
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_A
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_B
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_C
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_D
import com.azatberdimyradov.openfoodfacts.utils.Constants.ECOSCORE_E
import com.azatberdimyradov.openfoodfacts.utils.Constants.FAT
import com.azatberdimyradov.openfoodfacts.utils.Constants.HIGH
import com.azatberdimyradov.openfoodfacts.utils.Constants.LOW
import com.azatberdimyradov.openfoodfacts.utils.Constants.MODERATE
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_1
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_2
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_3
import com.azatberdimyradov.openfoodfacts.utils.Constants.NOVA_4
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_A
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_B
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_C
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_D
import com.azatberdimyradov.openfoodfacts.utils.Constants.NUTRISCORE_E
import com.azatberdimyradov.openfoodfacts.utils.Constants.SALT
import com.azatberdimyradov.openfoodfacts.utils.Constants.SATURATED_FAT
import com.azatberdimyradov.openfoodfacts.utils.Constants.SUGARS
import com.google.android.material.snackbar.Snackbar
import java.util.*


fun ProductResponse.convertToProductItem(): ProductItem {
    return ProductItem(
        productName = this.product.product_name,
        brandName = this.product.brands,
        quantity = this.product.quantity,
        barcode = this.product.code,
        imageUrl = this.product.image_front_url,
        nutriscore = this.product.nutriscore_grade,
        id = this.product.code.toInt()
    )
}

fun showSnackBar(message: String?, view: View) {
    Snackbar.make(
        view, message ?: "Error", Snackbar.LENGTH_SHORT
    ).show()
    println(message)
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

fun setAdditivesText(product: ProductResponse, fragment: Fragment) = buildSpannedString {
    bold {
        append(fragment.getString(R.string.txtAdditives))
    }
    product.product.additives_original_tags.forEach {
        append("\n")
        val additiveName = it.substring(it.lastIndexOf(":") + 1)
        append(
            additiveName.substring(0, 1).uppercase(Locale.getDefault())
                    + additiveName.substring(1)
        )
        append(" - ")
        product.product.ingredients.forEach { ingredient ->
            if (ingredient.id == it) {
                append(ingredient.text)
            } else if (ingredient.id == it.substring(0, it.length - 1)) {
                append(ingredient.text)
            }
        }
    }
}

fun setCategories(product: ProductResponse, fragment: Fragment) = buildSpannedString {
    bold {
        append(fragment.getString(R.string.txtCategories))
    }
    append(product.product.categories)
}

fun setIngredientList(product: ProductResponse, fragment: Fragment) = buildSpannedString {
    append("${fragment.getString(R.string.txtIngredientsList)} ")
    append(product.product.ingredients_text)
}

fun setVitamins(product: ProductResponse, fragment: Fragment) = buildSpannedString {
    bold { append(fragment.getString(R.string.txtVitamins)) }
    product.product.vitamins_tags.forEach {
        val vitaminName = it.substring(it.lastIndexOf(":") + 1)
        append("$vitaminName , ")
    }
}

fun setNovaGroupInfo(product: ProductResponse, fragment: Fragment) = buildSpannedString {
    val replacedText = product.product.nova_groups_tags[0].replace('-', ' ')
    val novaGroupInfo = replacedText.substring(replacedText.lastIndexOf(":") + 1)
    bold { append("${fragment.getString(R.string.txtGroup)} $novaGroupInfo") }
}

fun Product.convertNutrientLevelList(): List<NutrientLevelItem> {
    val list = mutableListOf<NutrientLevelItem>()
    list.add(
        NutrientLevelItem(
            FAT,
            nutrient_levels.fat,
            nutriments.fat.toDouble(),
            nutriments.fat_unit
        )
    )
    list.add(NutrientLevelItem(SALT, nutrient_levels.salt, nutriments.salt, nutriments.salt_unit))
    list.add(
        NutrientLevelItem(
            SATURATED_FAT,
            nutrient_levels.saturated_fat,
            nutriments.saturated_fat.toDouble(),
            nutriments.saturated_fat_unit
        )
    )
    list.add(
        NutrientLevelItem(
            SUGARS,
            nutrient_levels.sugars,
            nutriments.sugars.toDouble(),
            nutriments.sugars_unit
        )
    )
    return list
}

fun Nutriments.convertNutritionFactItemList(): List<NutritionFactsItem> {
    val list = mutableListOf<NutritionFactsItem>()
    list.add(
        NutritionFactsItem(
            name = "Carbohydrates",
            value = this.carbohydrates_100g.toDouble(),
            value_unit = carbohydrates_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Energy in calories (kcal)",
            value = this.energy_kcal_100g.toDouble(),
            value_unit = energy_kcal_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Fat",
            value = this.fat_100g.toDouble(),
            value_unit = fat_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Saturated fat",
            value = saturated_fat_100g.toDouble(),
            value_unit = saturated_fat_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Sugar",
            value = sugars_100g.toDouble(),
            value_unit = sugars_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Dietary fiber",
            value = fiber_100g.toDouble(),
            value_unit = fiber_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Proteins",
            value = proteins_100g.toDouble(),
            value_unit = proteins_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Sodium",
            value = sodium_100g,
            value_unit = sodium_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Salt",
            value = salt_100g,
            value_unit = salt_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Vitamin PP",
            value = vitamin_pp_100g,
            value_unit = vitamin_pp_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Vitamin B6",
            value = vitamin_b6_100g,
            value_unit = vitamin_b6_unit
        )
    )
    list.add(
        NutritionFactsItem(
            name = "Pantothenic acid",
            value = pantothenic_acid_100g,
            value_unit = pantothenic_acid_unit
        )
    )
    return list
}

fun NutrientLevelItem.getDecRes() = when (this.category) {
    MODERATE -> R.string.txtNutritionLevelModerate
    LOW -> R.string.txtNutritionLevelLow
    HIGH -> R.string.txtNutritionLevelHigh
    else -> 0
}

fun NutrientLevelItem.getImgRes() = when (this.category) {
    MODERATE -> R.drawable.moderate
    LOW -> R.drawable.low
    HIGH -> R.drawable.high
    else -> R.drawable.default_circle
}