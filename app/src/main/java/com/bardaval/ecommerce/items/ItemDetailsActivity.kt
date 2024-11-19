package com.bardaval.ecommerce.items

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.R
import com.bumptech.glide.Glide

class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val itemName = intent.getStringExtra("itemName")
        val itemDescription = intent.getStringExtra("itemDescription")
        val itemPrice = intent.getDoubleExtra("itemPrice", 0.0)
        val itemImageUrl = intent.getStringExtra("itemImageUrl")

        val itemNameView: TextView = findViewById(R.id.itemName)
        val itemDescriptionView: TextView = findViewById(R.id.itemDescription)
        val itemPriceView: TextView = findViewById(R.id.itemPrice)
        val itemImageView: ImageView = findViewById(R.id.itemImage)

        itemNameView.text = itemName
        itemDescriptionView.text = itemDescription
        itemPriceView.text = "$$itemPrice"

        // Load image using Glide
        Glide.with(this)
            .load(itemImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemImageView)

        // Payment options
        val payButton: Button = findViewById(R.id.payButton)
        payButton.setOnClickListener {
            val paymentIntent = Intent(Intent.ACTION_VIEW)
            paymentIntent.data = Uri.parse("upi://pay?pa=example@upi&pn=MerchantName&am=$itemPrice")
            startActivity(paymentIntent)
        }
    }
}
