package com.bardaval.ecommerce.items

import retrofit2.Call
import retrofit2.http.GET

interface ApiItemService {
    @GET("products")  // This endpoint should return a list of products
    fun getItems(): Call<List<Item>>
}
