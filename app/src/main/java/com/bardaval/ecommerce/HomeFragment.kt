package com.bardaval.ecommerce
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.items.ListofItems


class HomeFragment : Fragment() {

    private lateinit var viewFlipper: ViewFlipper
    private val handler = Handler()
    private var isFlippingForward = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewFlipper = view.findViewById(R.id.viewFlipper)

        // Set click listeners for each child view in the ViewFlipper
        for (i in 0 until viewFlipper.childCount) {
            val child = viewFlipper.getChildAt(i)
            child.setOnClickListener {
                openSignInActivity()  // Open SignInActivity when a ViewFlipper item is clicked
            }
        }

        // Set click listeners for HorizontalScrollView items (images)
        setHorizontalScrollViewClickListeners(view)

        // Start automatic flipping
        startFlipping()

        return view
    }

    private fun setHorizontalScrollViewClickListeners(view: View) {
        // Assuming each image has a unique ID, set click listeners for them
        val horizontalImage1: ImageView = view.findViewById(R.id.cardimg21)
        val horizontalImage2: ImageView = view.findViewById(R.id.cardimg22)
        val horizontalImage3: ImageView = view.findViewById(R.id.cardimg23)
        val horizontalImage4: ImageView = view.findViewById(R.id.cardimg24)

        horizontalImage1.setOnClickListener { openSignUpActivity() }
        horizontalImage2.setOnClickListener { openSignUpActivity() }
        horizontalImage3.setOnClickListener { openSignUpActivity() }
        horizontalImage3.setOnClickListener { openSignUpActivity() }
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

    private fun openSignInActivity() {
        val intent = Intent(requireContext(), ListofItems::class.java)
        startActivity(intent) // Start the SignInActivity
    }

    private fun openSignUpActivity() {
        val intent = Intent(requireContext(), ListofItems::class.java)
        startActivity(intent) // Start the SignUpActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null) // Clean up handler
    }
}










/*package com.bardaval.ecommerce

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var viewFlipper: ViewFlipper
    private val handler = Handler()
    private var isFlippingForward = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

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
