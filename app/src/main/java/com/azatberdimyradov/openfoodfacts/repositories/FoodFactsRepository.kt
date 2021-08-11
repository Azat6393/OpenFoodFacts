package com.azatberdimyradov.openfoodfacts.repositories

import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FoodFactsRepository {

    suspend fun insertProductItemIntoDb(productItem: ProductItem)

    suspend fun deleteProductItemFromDb(productItem: ProductItem)

    fun observeAllProductItem(): Flow<List<ProductItem>>

    suspend fun getProductByBarcode(barcode: String): Resource<ProductResponse>

}