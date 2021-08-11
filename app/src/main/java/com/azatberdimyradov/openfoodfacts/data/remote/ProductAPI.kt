package com.azatberdimyradov.openfoodfacts.data.remote

import com.azatberdimyradov.openfoodfacts.data.remote.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("/api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(
        @Path("barcode") barcode: String
    ): Response<ProductResponse>

}