package com.bardaval.ecommerce.activity

import android.os.Bundle
import android.os.Handler
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.R

class FashionActivity : AppCompatActivity() {

    private lateinit var viewFlipper: ViewFlipper
    private val handler = Handler()
    private var isFlippingForward = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fashion)
        viewFlipper = findViewById(R.id.viewFlipper)
        startFlipping()
    }
    private fun startFlipping() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isFlippingForward) {
                    viewFlipper.setInAnimation(this@FashionActivity, android.R.anim.slide_in_left)
                    viewFlipper.setOutAnimation(this@FashionActivity, android.R.anim.slide_out_right)
                    viewFlipper.showNext()
                } else {
                    viewFlipper.setInAnimation(this@FashionActivity, R.anim.slide_in_left)
                    viewFlipper.setOutAnimation(this@FashionActivity, R.anim.slide_out_left)
                    viewFlipper.showPrevious()
                }
                if (viewFlipper.displayedChild == viewFlipper.childCount - 1) {
                    isFlippingForward = false
                } else if (viewFlipper.displayedChild == 0) {
                    isFlippingForward = true
                }
                handler.postDelayed(this, 2000)
            }
        }, 2000)
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
