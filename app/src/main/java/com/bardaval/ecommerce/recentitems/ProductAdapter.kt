package com.bardaval.ecommerce.recentitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bardaval.ecommerce.R
import com.bumptech.glide.Glide

class ProductAdapter(private val productList: MutableList<Product>, private val onItemClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)

        init {
            itemView.setOnClickListener {
                val product = productList[adapterPosition]
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.title
        holder.productPrice.text = "$${product.price}"
        // Use Glide to load the product image
        Glide.with(holder.itemView.context).load(product.image).into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
