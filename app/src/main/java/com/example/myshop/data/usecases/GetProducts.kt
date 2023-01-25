package com.example.myshop.data.usecases

import com.example.myshop.data.mappers.ProductModelDataMapper
import com.example.myshop.data.model.ProductModel
import com.example.myshop.data.repositories.ProductsRepository
import javax.inject.Inject

interface GetProducts {
    suspend fun call(): List<ProductModel>
}

class GetProductsImpl @Inject constructor(
    private val repository: ProductsRepository,
    private val postMapper: ProductModelDataMapper,

    ) : GetProducts {

    override suspend fun call() =
        postMapper.transform(repository.getProducts())
}