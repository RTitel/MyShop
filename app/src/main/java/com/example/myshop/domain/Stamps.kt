package com.example.myshop.domain

import com.google.gson.annotations.SerializedName


data class Stamps(

    @SerializedName("cluster_id") var clusterId: Int? = null,
    @SerializedName("text_color") var textColor: String? = null,
    @SerializedName("background_color") var backgroundColor: String? = null,
    @SerializedName("opacity") var opacity: Int? = null,
    @SerializedName("template") var template: String? = null,
    @SerializedName("label") var label: String? = null

)