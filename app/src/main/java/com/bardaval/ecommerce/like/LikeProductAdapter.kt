package com.bardaval.ecommerce.like

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bardaval.ecommerce.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LikeProductAdapter(private val context: Context, private val productList: List<ProductsData>) :
    RecyclerView.Adapter<LikeProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image)
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val addCartButton: ImageButton = view.findViewById(R.id.add_cart_button)
        val videoPlayButton: ImageButton = view.findViewById(R.id.video_play_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.like_product_item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"
        holder.productImage.load(product.imageUrl) // Load product image using Coil

        holder.videoPlayButton.setOnClickListener {
            showVideoDialog(product.videoUrl)
        }

        // Add to cart button functionality
        holder.addCartButton.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun showVideoDialog(videoUrl: String) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_video, null)
        val videoView = dialogView.findViewById<android.widget.VideoView>(R.id.video_view)

        val builder = android.app.AlertDialog.Builder(context)
            .setTitle("Video Player")
            .setView(dialogView)
            .setCancelable(true)

        val alertDialog = builder.create()
        alertDialog.show()

        videoView.setVideoURI(Uri.parse(videoUrl))


        videoView.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // Pause the video on second click
            } else {
                videoView.start()
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                videoView.setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                }
            }
        }
    }
}
