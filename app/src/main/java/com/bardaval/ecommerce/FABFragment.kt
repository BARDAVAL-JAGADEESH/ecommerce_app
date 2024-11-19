package com.bardaval.ecommerce

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bardaval.ecommerce.activity.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FABFragment : Fragment(R.layout.fragment_fab) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabViewCart: FloatingActionButton = view.findViewById(R.id.fab_view_cart)

        fabViewCart.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, CartItemsFragment())
            fragmentTransaction.addToBackStack(null) // Optional: add to back stack for navigation
            fragmentTransaction.commit()
        }

        // Handle back button press to navigate to MainActivity
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                }
            }
        )
    }
}
