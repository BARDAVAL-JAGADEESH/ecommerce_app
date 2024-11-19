package com.bardaval.ecommerce.recentitems

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int = 1000): List<Product>
}



/*package com.bardaval.ecommerce

import retrofit2.http.GET

interface ApiService {
    @GET("products") // Endpoint to fetch products
    suspend fun getProducts(): List<Product>
}
*/