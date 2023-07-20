package com.example.phonenew.data.network

import com.example.phonenew.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiClient {
    @GET("/Himuravidal/FakeAPIdata/products")
    suspend fun getAllProducts() : Response<List<ProductModel>>

    @GET("/Himuravidal/FakeAPIdata/details/{id}")
    suspend fun getProductDetail(@Path("id") productId : Int) : Response<ProductModel>
}