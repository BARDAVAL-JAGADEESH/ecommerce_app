package com.bardaval.ecommerce.items

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bardaval.ecommerce.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListofItems : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var progressBar: ProgressBar // Declare ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listof_items)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 items per row
        progressBar = findViewById(R.id.progressBar) // Initialize ProgressBar
    }

    override fun onResume() {
        super.onResume()
        fetchItemsFromApi() // Fetch data whenever the activity is resumed
    }

    private fun fetchItemsFromApi() {
        // Show the progress bar while fetching data
        progressBar.visibility = View.VISIBLE

        val call = RetrofitClient.apiItemService.getItems()

        call.enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                // Hide the progress bar once the data is loaded
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val items = response.body()
                    items?.let {
                        // Set up RecyclerView with fetched data
                        adapter = ItemAdapter(it) { item ->
                            val intent = Intent(this@ListofItems, ItemDetailsActivity::class.java).apply {
                                putExtra("itemName", item.title)
                                putExtra("itemDescription", item.description)
                                putExtra("itemPrice", item.price)
                                putExtra("itemImageUrl", item.image) // Use the image URL from the API
                            }
                            startActivity(intent)
                        }
                        recyclerView.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@ListofItems, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                // Hide the progress bar if there was an error fetching data
                progressBar.visibility = View.GONE

                Log.e("ListofItems", "Error fetching data", t)
                Toast.makeText(this@ListofItems, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
