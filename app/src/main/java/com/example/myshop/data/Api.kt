package com.example.myshop.data

import com.example.myshop.domain.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
    @GET("/mobile/8/g/women/clothing")
    @Headers("x-mobile-app-locale:ro_RO")
    suspend fun getProducts(): Response<ProductsList>
}