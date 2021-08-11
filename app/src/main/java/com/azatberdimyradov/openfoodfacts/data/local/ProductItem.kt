package com.azatberdimyradov.openfoodfacts.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "product_items")
@Parcelize
data class ProductItem(
    val productName: String,
    val brandName: String,
    val quantity: String,
    val barcode: String,
    val imageUrl: String,
    val nutriscore: String,
    val added: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable {
    val addedDateFormatted: String
        get() = DateFormat.getDateInstance().format(added)
}
