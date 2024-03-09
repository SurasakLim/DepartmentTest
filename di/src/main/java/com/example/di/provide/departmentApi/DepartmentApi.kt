package com.example.di.provide.departmentApi

import com.example.core.departmentModel.DepartmentResponseItem
import com.example.core.departmentModel.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepartmentApi {

    @GET("/api/v1/departments")
    suspend fun getDepartmentCarousel(): Response<List<DepartmentResponseItem>>

    @GET("/api/v1/departments/{id}/products")
    suspend fun getProductList(
        @Path("id") id: String
    ): Response<List<Product>>
}
