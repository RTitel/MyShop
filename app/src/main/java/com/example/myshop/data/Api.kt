package com.example.myshop.data

import com.example.myshop.domain.ProductsList
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/women/clothing")
    suspend fun getProducts(): Response<ProductsList>
}