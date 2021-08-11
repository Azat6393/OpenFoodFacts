package com.azatberdimyradov.openfoodfacts.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.openfoodfacts.data.local.ProductItem
import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import com.azatberdimyradov.openfoodfacts.repositories.FoodFactsRepository
import com.azatberdimyradov.openfoodfacts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OpenFoodFactsViewModel @ViewModelInject constructor(
    private val repo: FoodFactsRepository
): ViewModel() {

    private val _productResponse = MutableStateFlow<Resource<ProductResponse>>(Resource.Empty())
    val productResponse: StateFlow<Resource<ProductResponse>> = _productResponse

    val observeProductItems = repo.observeAllProductItem()

    fun insertProductItemIntoDb(productItem: ProductItem) = viewModelScope.launch {
        repo.insertProductItemIntoDb(productItem)
    }

    fun deleteProductItemFromDb(productItem: ProductItem) = viewModelScope.launch {
        repo.deleteProductItemFromDb(productItem)
    }

    fun getProductByBarcode(barcode: String) = viewModelScope.launch {
        _productResponse.value = Resource.Loading()
        val result = repo.getProductByBarcode(barcode)
        _productResponse.value = result
    }

}