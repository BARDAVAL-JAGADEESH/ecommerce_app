
package com.bardaval.ecommerce
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartDataFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var cartItems: MutableList<Product>  // Cart data

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_cart)

        cartItems = getCartItems()  // Fetch cart items (could be from memory or database)

        // Set up RecyclerView to display the cart items
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CartAdapter(cartItems)
    }

    // Sample function to get cart items
    private fun getCartItems(): MutableList<Product> {
        // Simulating cart items (this can be fetched from a database, shared prefs, etc.)
        return mutableListOf(
            Product("Product 1", "Description of Product 1", 29.99),
            Product("Product 2", "Description of Product 2", 19.99)
        )
    }

    // Data class for Product (can be replaced with your actual model)
    data class Product(val name: String, val description: String, val price: Double)
}
