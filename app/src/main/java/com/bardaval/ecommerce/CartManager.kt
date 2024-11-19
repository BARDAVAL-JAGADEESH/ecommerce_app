package com.bardaval.ecommerce

import com.bardaval.ecommerce.recentitems.Product

object CartManager {
    val cartItems: MutableList<Product> = mutableListOf()

    fun addToCart(product: Product) {
        cartItems.add(product)
    }
}
