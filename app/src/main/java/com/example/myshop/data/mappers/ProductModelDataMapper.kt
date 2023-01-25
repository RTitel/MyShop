package com.example.myshop.data.mappers

import com.example.myshop.data.model.ProductModel
import com.example.myshop.domain.Product
import javax.inject.Inject

class ProductModelDataMapper @Inject constructor() {
    fun transform(product: Product): ProductModel =
        ProductModel(
            product.productId,
            product.productBrand,
            product.productName,
            product.productImages?.thumb?.firstOrNull()
        )

    fun transform(products: List<Product>): List<ProductModel> =
        products.map {
            transform(it)
        }
}