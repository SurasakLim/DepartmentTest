package com.example.core.departmentModel

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

@Keep
data class ProductsResponse(
    @SerializedName("departmentId")
    val products: List<Product>? = emptyList(),
)

@Keep
data class Product(
    @SerializedName("departmentId")
    val departmentId: String?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("type")
    val type: String?
)