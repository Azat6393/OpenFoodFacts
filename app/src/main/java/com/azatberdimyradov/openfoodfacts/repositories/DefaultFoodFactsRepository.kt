package com.azatberdimyradov.openfoodfacts.repositories

import com.azatberdimyradov.openfoodfacts.data.local.ProductDao
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.ProductAPI
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class DefaultFoodFactsRepository @Inject constructor(
    private val productDao: ProductDao,
    private val productApi: ProductAPI
): FoodFactsRepository {

    override suspend fun insertProductItemIntoDb(productItem: ProductItem) {
        productDao.insertProductDao(productItem)
    }

    override suspend fun deleteProductItemFromDb(productItem: ProductItem) {
        productDao.deleteProductItem(productItem)
    }

    override fun observeAllProductItem(): Flow<List<ProductItem>> {
        return productDao.observeAllProductItems()
    }

    override suspend fun getProductByBarcode(barcode: String): Resource<ProductResponse> {
        return try {
            val response = productApi.getProductByBarcode(barcode)
            if (response.isSuccessful){
                response.body()?.let {
                    if (it.status == 1){
                        return@let Resource.Success(it)
                    }
                    return@let Resource.Error("product not found")
                } ?: Resource.Error(response.errorBody().toString())
            }else {
                Resource.Error(response.errorBody().toString())
            }
        }catch (e: Exception){
            Resource.Error(e.localizedMessage)
        }
    }
}