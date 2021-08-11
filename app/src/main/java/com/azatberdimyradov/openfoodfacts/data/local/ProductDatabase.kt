package com.azatberdimyradov.openfoodfacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ProductItem::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDao: RoomDatabase() {

    abstract fun productDao(): ProductDao
}