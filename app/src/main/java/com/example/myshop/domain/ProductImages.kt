package com.example.myshop.domain

import com.google.gson.annotations.SerializedName


data class ProductImages(

    @SerializedName("listing") var listing: ArrayList<String> = arrayListOf(),
    @SerializedName("detail") var detail: ArrayList<String> = arrayListOf(),
    @SerializedName("zoom") var zoom: ArrayList<String> = arrayListOf(),
    @SerializedName("thumb") var thumb: ArrayList<String> = arrayListOf()

)