package com.cesarzorzon.busquedaproductosml.data.network

import com.cesarzorzon.busquedaproductosml.data.model.ProductosResponse
import com.cesarzorzon.busquedaproductosml.data.model.DescriptionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("sites/MLA/search")
    suspend fun getProducts(@Query("q") id: String): Response<ProductosResponse>

    @GET
    suspend fun getDescription(@Url url:String) :Response<DescriptionResponse>

}