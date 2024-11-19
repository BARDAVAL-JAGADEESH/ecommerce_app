package com.bardaval.ecommerce

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bardaval.ecommerce.activity.MainActivity
import com.bardaval.ecommerce.items.ItemDetailsActivity
import com.bardaval.ecommerce.recentitems.ApiService
import com.bardaval.ecommerce.recentitems.NetworkUtils
import com.bardaval.ecommerce.recentitems.Product
import com.bardaval.ecommerce.recentitems.ProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartItemsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        val backButton: ImageView = view.findViewById(R.id.back_button)

        // Handle custom UI back button click
        backButton.setOnClickListener {
            navigateToMainActivity()
        }

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        productAdapter = ProductAdapter(productList) { product ->
            openProductDetails(product)
        }
        recyclerView.adapter = productAdapter

        fetchProducts()

        // Handle device back button press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMainActivity()
            }
        })
    }

    private fun fetchProducts() {
        if (!NetworkUtils.isInternetAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_LONG).show()
            return
        }

        progressBar.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Fetch more products by setting the limit to 1000
                val products = apiService.getProducts(limit = 1000)
                withContext(Dispatchers.Main) {
                    productList.clear()
                    productList.addAll(products)
                    productAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to load products", Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun openProductDetails(product: Product) {
        val intent = Intent(requireContext(), ItemDetailsActivity::class.java)
        intent.putExtra("itemName", product.title)
        intent.putExtra("itemPrice", product.price)
        intent.putExtra("itemImageUrl", product.image)
        startActivity(intent)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }
}
