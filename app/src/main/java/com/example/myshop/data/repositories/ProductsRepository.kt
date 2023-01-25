package com.example.myshop.data.repositories

import com.example.myshop.data.Api
import com.example.myshop.data.NetworkHandler
import com.example.myshop.domain.Product
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val api: Api,
    private val networkHandler: NetworkHandler
) {

    suspend fun getProducts(): List<Product> {
        // Won't do a complex error handling mechanism as it is out of scope at this moment
        if (!networkHandler.isNetworkAvailable()) return emptyList()

        val response = api.getProducts()
        if (response.isSuccessful) {
            response.body()?.let {
                return it.products
            }
        }
        return emptyList()
    }

}