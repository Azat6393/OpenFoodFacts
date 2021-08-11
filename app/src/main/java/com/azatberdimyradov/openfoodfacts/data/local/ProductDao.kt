package com.azatberdimyradov.openfoodfacts.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDao(productItem: ProductItem)

    @Delete
    suspend fun deleteProductItem(productItem: ProductItem)

    @Query("SELECT * FROM product_items")
    fun observeAllProductItems(): Flow<List<ProductItem>>

}