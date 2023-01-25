package com.example.myshop.domain

import com.google.gson.annotations.SerializedName


data class Product(

    @SerializedName("stamps") var stamps: ArrayList<Stamps> = arrayListOf(),
    @SerializedName("product_id") var productId: Int? = null,
    @SerializedName("product_name") var productName: String? = null,
    @SerializedName("product_images") var productImages: ProductImages? = ProductImages(),
    @SerializedName("product_price") var productPrice: Double? = null,
    @SerializedName("product_original_price") var productOriginalPrice: Double? = null,
    @SerializedName("product_type") var productType: String? = null,
    @SerializedName("product_stock_state") var productStockState: String? = null,
    @SerializedName("product_brand") var productBrand: String? = null,
    @SerializedName("product_brand_id") var productBrandId: Int? = null,
    @SerializedName("product_tag_id") var productTagId: Int? = null,
    @SerializedName("sku") var sku: String? = null,
    @SerializedName("discount_type") var discountType: String? = null,
    @SerializedName("hv") var hv: Int? = null,
    @SerializedName("has_vrs") var hasVrs: Int? = null,
    @SerializedName("classification_name") var classificationName: String? = null,
    @SerializedName("classification") var classification: String? = null,
    @SerializedName("classification_id") var classificationId: String? = null,
    @SerializedName("sub_classification") var subClassification: String? = null,
    @SerializedName("sub_classification_id") var subClassificationId: String? = null,
    @SerializedName("tag_name") var tagName: String? = null,
    @SerializedName("product_is_new") var productIsNew: Int? = null,
    @SerializedName("product_discount") var productDiscount: Int? = null,
    @SerializedName("screen_title") var screenTitle: String? = null

)