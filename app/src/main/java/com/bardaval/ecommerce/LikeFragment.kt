package com.bardaval.ecommerce

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bardaval.ecommerce.activity.MainActivity
import com.bardaval.ecommerce.like.LikeProductAdapter
import com.bardaval.ecommerce.like.ProductsData

class LikeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_like, container, false)

        // Set up the back button in the fragment layout
        val backButton: ImageButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        // Set up the RecyclerView with product data
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val productList = getProductList()
        val adapter = LikeProductAdapter(requireContext(), productList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        // Handle the back button press (device back button)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navigate to MainActivity when the back button is pressed
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        })

        return view
    }

    // Sample product list data
    private fun getProductList(): List<ProductsData> {
        return listOf(
            ProductsData("Tshirt", 10.0, "https://img.freepik.com/free-vector/sportswear-store-shelf_1284-4732.jpg?ga=GA1.1.1670570126.1729872919&semt=ais_hybrid", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Mens Tshirt", 20.0, "https://images.pexels.com/photos/8532611/pexels-photo-8532611.jpeg?auto=compress&cs=tinysrgb&w=600", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Women Tshirt", 12.0, "https://cdn.pixabay.com/photo/2024/05/09/13/35/ai-generated-8751040_640.png", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Dell ", 90.0, "https://tse3.mm.bing.net/th?id=OIP.E7s9xyTEf7KV97V81H2w7wHaFU&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Hp Laptop", 99.0, "https://tse4.mm.bing.net/th?id=OIP.EWHEXEl5keLQvd_r3tH0GQHaFr&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Dell laptop", 93.0, "https://tse1.mm.bing.net/th?id=OIP.jcY8V4CIxH8REjzVWVLwQwHaEI&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Himalaya", 6.0, "https://tse1.mm.bing.net/th?id=OIP.2Y1MN_pQRlItAkmBK0z7BgHaHa&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Himalaya face wash", 15.0, "https://tse4.mm.bing.net/th?id=OIP.zqLlAloJ2WXRh2CGVBFjQQHaGE&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Samsung", 80.0, "https://tse2.mm.bing.net/th?id=OIP.tdPjthNLE8IRxwNpBAsVvwHaEK&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Apple", 90.0, "https://tse1.mm.bing.net/th?id=OIP.aBMr6Nzp_i-y0ISMZCfLlAHaDN&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Apple", 77.0, "https://tse3.mm.bing.net/th?id=OIP.atUbRIOIuXZ0h_oRDtPTSwHaHa&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("washing machine", 80.0, "https://cdn.pixabay.com/photo/2016/10/31/18/50/washing-machine-1786385_640.png", "https://example.com/video2.mp4"),
            ProductsData("Air cooler", 30.0, "https://m.media-amazon.com/images/I/71rpFByiJBL.jpg", "https://example.com/video2.mp4"),
            ProductsData("Saree", 43.0, "https://i.pinimg.com/originals/6c/31/0a/6c310a0c950262dacd447f59e057ea64.jpg", "https://example.com/video2.mp4"),
            ProductsData("Saree", 22.0, "https://3.bp.blogspot.com/-CEpzYppQ55o/UMi-54j5_yI/AAAAAAAAFYI/6TJsTYx2scY/s1600/Indian+Bridal+Sarees+%25283%2529.jpg", "https://example.com/video2.mp4"),
            ProductsData("Refrigerator", 50.0, "https://tse1.mm.bing.net/th?id=OIP.60oAXfdblbUcKfa9YW1vKAHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Refrigerator", 40.0, "https://tse2.mm.bing.net/th?id=OIP.9LeouWTxA-kscJqXIYoLXQHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 7.0, "https://tse2.mm.bing.net/th?id=OIP.B9u6D914hzru0q6x3JSmQAHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 6.0, "https://tse1.mm.bing.net/th?id=OIP.ePw-HdP8CCJYZe9HKFFDfgHaGJ&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 6.0, "https://tse1.mm.bing.net/th?id=OIP.ePw-HdP8CCJYZe9HKFFDfgHaGJ&pid=Api&P=0&h=180", "https://example.com/video2.mp4")
        )
    }
}






/*package com.bardaval.ecommerce
import android.content.Intent
import com.bardaval.ecommerce.like.ProductsData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bardaval.ecommerce.activity.MainActivity
import com.bardaval.ecommerce.like.LikeProductAdapter

class LikeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_like, container, false)

        // Back button click listener
        val backButton: ImageButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val productList = getProductList()
        val adapter = LikeProductAdapter(requireContext(), productList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        return view
    }

    private fun getProductList(): List<ProductsData> {

        return listOf(
            ProductsData("Tshirt", 10.0, "https://img.freepik.com/free-vector/sportswear-store-shelf_1284-4732.jpg?ga=GA1.1.1670570126.1729872919&semt=ais_hybrid", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Mens Tshirt", 20.0, "https://images.pexels.com/photos/8532611/pexels-photo-8532611.jpeg?auto=compress&cs=tinysrgb&w=600", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Women Tshirt", 12.0, "https://cdn.pixabay.com/photo/2024/05/09/13/35/ai-generated-8751040_640.png", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Dell ", 90.0, "https://tse3.mm.bing.net/th?id=OIP.E7s9xyTEf7KV97V81H2w7wHaFU&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Hp Laptop", 99.0, "https://tse4.mm.bing.net/th?id=OIP.EWHEXEl5keLQvd_r3tH0GQHaFr&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Dell laptop", 93.0, "https://tse1.mm.bing.net/th?id=OIP.jcY8V4CIxH8REjzVWVLwQwHaEI&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Himalaya", 6.0, "https://tse1.mm.bing.net/th?id=OIP.2Y1MN_pQRlItAkmBK0z7BgHaHa&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Himalaya face wash", 15.0, "https://tse4.mm.bing.net/th?id=OIP.zqLlAloJ2WXRh2CGVBFjQQHaGE&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Samsung", 80.0, "https://tse2.mm.bing.net/th?id=OIP.tdPjthNLE8IRxwNpBAsVvwHaEK&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Apple", 90.0, "https://tse1.mm.bing.net/th?id=OIP.aBMr6Nzp_i-y0ISMZCfLlAHaDN&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("Apple", 77.0, "https://tse3.mm.bing.net/th?id=OIP.atUbRIOIuXZ0h_oRDtPTSwHaHa&pid=Api&P=0&h=180", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            ProductsData("washing machine", 80.0, "https://cdn.pixabay.com/photo/2016/10/31/18/50/washing-machine-1786385_640.png", "https://example.com/video2.mp4"),
            ProductsData("Air cooler", 30.0, "https://m.media-amazon.com/images/I/71rpFByiJBL.jpg", "https://example.com/video2.mp4"),
            ProductsData("Saree", 43.0, "https://i.pinimg.com/originals/6c/31/0a/6c310a0c950262dacd447f59e057ea64.jpg", "https://example.com/video2.mp4"),
            ProductsData("Saree", 22.0, "https://3.bp.blogspot.com/-CEpzYppQ55o/UMi-54j5_yI/AAAAAAAAFYI/6TJsTYx2scY/s1600/Indian+Bridal+Sarees+%25283%2529.jpg", "https://example.com/video2.mp4"),
            ProductsData("Refrigerator", 50.0, "https://tse1.mm.bing.net/th?id=OIP.60oAXfdblbUcKfa9YW1vKAHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Refrigerator", 40.0, "https://tse2.mm.bing.net/th?id=OIP.9LeouWTxA-kscJqXIYoLXQHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 7.0, "https://tse2.mm.bing.net/th?id=OIP.B9u6D914hzru0q6x3JSmQAHaHa&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 6.0, "https://tse1.mm.bing.net/th?id=OIP.ePw-HdP8CCJYZe9HKFFDfgHaGJ&pid=Api&P=0&h=180", "https://example.com/video2.mp4"),
            ProductsData("Curtains", 6.0, "https://tse1.mm.bing.net/th?id=OIP.ePw-HdP8CCJYZe9HKFFDfgHaGJ&pid=Api&P=0&h=180", "https://example.com/video2.mp4")
        )
    }
}









*/










/*
package com.bardaval.ecommerce

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class LikeFragment : Fragment(R.layout.fragment_like) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)

        // Dummy product list
        val productList = listOf(
            Productsdata("Product 1", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$10.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk=")
        )

        for (product in productList) {
            addProductCard(gridLayout, product)
        }
    }

    private fun addProductCard(gridLayout: GridLayout, product: Productsdata) {
        val cardView = layoutInflater.inflate(R.layout.like_product_item_layout, gridLayout, false) as CardView
        val productName = cardView.findViewById<TextView>(R.id.productName)
        val productPrice = cardView.findViewById<TextView>(R.id.productPrice)
        val productImage = cardView.findViewById<ImageView>(R.id.productImage)
        val addToCartButton = cardView.findViewById<ImageButton>(R.id.addToCartButton)
        val videoClipButton = cardView.findViewById<Button>(R.id.videoClipButton)
        val videoView = cardView.findViewById<VideoView>(R.id.videoView)

        productName.text = product.name
        productPrice.text = product.price

        // Load product image using Glide
        Glide.with(this)
            .load(product.imageLink)
            .into(productImage)

        // Handle Add to Cart button click
        addToCartButton.setOnClickListener {
            Toast.makeText(requireContext(), "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Handle Watch Video button click
        videoClipButton.setOnClickListener {
            if (videoView.visibility == View.GONE) {
                // Hide product info and show video
                toggleProductVisibility(cardView, View.GONE)
                videoView.visibility = View.VISIBLE
                playVideo(product.videoLink, videoView)
            } else {
                // Stop the video and show product details again
                videoView.stopPlayback()
                videoView.visibility = View.GONE
                toggleProductVisibility(cardView, View.VISIBLE)
            }
        }

        // Add the card to the grid layout
        gridLayout.addView(cardView)
    }

    private fun playVideo(videoUrl: String, videoView: VideoView) {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoUrl))

        videoView.start()

        // When video completes, return to product view
        videoView.setOnCompletionListener {
            // Hide video and return to product info view
            videoView.visibility = View.GONE
            toggleProductVisibility(videoView.parent as View, View.VISIBLE)
        }
    }

    private fun toggleProductVisibility(cardView: View, visibility: Int) {
        cardView.findViewById<ImageView>(R.id.productImage).visibility = visibility
        cardView.findViewById<TextView>(R.id.productName).visibility = visibility
        cardView.findViewById<TextView>(R.id.productPrice).visibility = visibility
        cardView.findViewById<Button>(R.id.videoClipButton).visibility = visibility
        cardView.findViewById<ImageButton>(R.id.addToCartButton).visibility = visibility
    }
}
*/










/*package com.bardaval.ecommerce


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikeFragment : Fragment(R.layout.fragment_like) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)


        val productList = listOf(
            Productsdata("Product 1", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$10.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk="),
            Productsdata("Product 2", "https://images.unsplash.com/photo-1615396899839-c99c121888b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGUlMjBjb21tZXJjZSUyMHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$15.99", "https://media.istockphoto.com/id/2164584591/video/india-woman-playinging-with-her-baby-at-home.mp4?s=mp4-640x640-is&k=20&c=csctwN__hQAd6LUbqt6dXSFlzLML5aT0TAWBTN_KFxk=")
        )

        productList.forEach { product ->
            val cardView = layoutInflater.inflate(R.layout.like_product_item_layout, null) as CardView
            val productImage: ImageView = cardView.findViewById(R.id.productImage)
            val productName: TextView = cardView.findViewById(R.id.productName)
            val productPrice: TextView = cardView.findViewById(R.id.productPrice)
            val addToCartButton: ImageButton = cardView.findViewById(R.id.addToCartButton)
            val videoClipButton: Button = cardView.findViewById(R.id.videoClipButton)

            productName.text = product.name
            productPrice.text = product.price
            Glide.with(this).load(product.imageLink).into(productImage)


            gridLayout.addView(cardView)

            videoClipButton.setOnClickListener {
                playVideo(product.videoLink)
            }

            addToCartButton.setOnClickListener {

            }
        }
    }

    private fun playVideo(videoLink: String) {
        val videoView: VideoView = requireView().findViewById(R.id.videoView)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)


        lifecycleScope.launch(Dispatchers.Main) {
            videoView.setVideoPath(videoLink)
            videoView.start()
        }
    }
}
*/




/*
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class LikeFragment : Fragment() {

    private lateinit var viewFlipper: ViewFlipper
    private val handler = Handler()
    private var isFlippingForward = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_like, container, false)

        viewFlipper = view.findViewById(R.id.viewFlipper)

        // Start automatic flipping
        startFlipping()

        return view
    }

    private fun startFlipping() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isFlippingForward) {
                    viewFlipper.setInAnimation(requireContext(), android.R.anim.slide_in_left)
                    viewFlipper.setOutAnimation(requireContext(), android.R.anim.slide_out_right)
                    viewFlipper.showNext()
                } else {
                    viewFlipper.setInAnimation(requireContext(), R.anim.slide_in_left)
                    viewFlipper.setOutAnimation(requireContext(), R.anim.slide_out_left)
                    viewFlipper.showPrevious()
                }

                // Switch direction after every 2 views flipped
                if (viewFlipper.displayedChild == viewFlipper.childCount - 1) {
                    isFlippingForward = false
                } else if (viewFlipper.displayedChild == 0) {
                    isFlippingForward = true
                }

                // Schedule the next flip
                handler.postDelayed(this, 2000) // Flip every 2 seconds
            }
        }, 2000) // Initial delay of 2 seconds
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null) // Clean up handler
    }
}*/
