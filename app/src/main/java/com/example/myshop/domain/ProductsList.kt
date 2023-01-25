package com.example.myshop.domain

import com.google.gson.annotations.SerializedName


data class ProductsList(
    @SerializedName("products") var products: ArrayList<Product> = arrayListOf()
)