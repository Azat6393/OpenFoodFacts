package com.azatberdimyradov.openfoodfacts.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "product_items")
@Parcelize
data class ProductItem(
    val productName: String? = null,
    val brandName: String? = null,
    val quantity: String? = null,
    val barcode: String,
    val imageUrl: String? = null,
    val nutriscore: String? = null,
    val added: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = false)
    val id: Long
) : Parcelable {
    val addedDateFormatted: String
        get() = DateFormat.getDateInstance().format(added)
}
