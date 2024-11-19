package com.bardaval.ecommerce.items

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://fakestoreapi.com"  // Replace with your API URL

    val apiItemService: ApiItemService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiItemService::class.java)
    }
}
