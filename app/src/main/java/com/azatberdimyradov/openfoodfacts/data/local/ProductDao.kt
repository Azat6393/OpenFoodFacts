package com.azatberdimyradov.openfoodfacts.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDao(productItem: ProductItem)

    @Delete
    suspend fun deleteProductItem(productItem: ProductItem)

    @Query("SELECT * FROM product_items")
    fun observeAllProductItems(): Flow<List<ProductItem>>

}