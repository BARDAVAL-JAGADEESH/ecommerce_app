package com.bardaval.ecommerce.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.roomdb.SignInActivity

class OnboardingScreen : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var skipBtn: Button

    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: OnboardViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)

        // Initialize UI elements
        backBtn = findViewById(R.id.backbtn)
        nextBtn = findViewById(R.id.nextbtn)
        skipBtn = findViewById(R.id.skipButton)

        backBtn.setOnClickListener {
            if (getItem(0) > 0) {
                mSlideViewPager.setCurrentItem(getItem(-1), true)
            }
        }

        nextBtn.setOnClickListener {
            if (getItem(0) < 3) {
                mSlideViewPager.setCurrentItem(getItem(1), true)
            } else {
                // Save onboarding completion status
                val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean("OnboardingCompleted", true) // Mark onboarding as completed
                    apply()
                }

                // Start MainActivity after onboarding
                val intent = Intent(this@OnboardingScreen, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        skipBtn.setOnClickListener {
            // Save onboarding completion status when skipped
            val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("OnboardingCompleted", true) // Mark onboarding as completed
                apply()
            }

            // Start MainActivity
            val intent = Intent(this@OnboardingScreen, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        // Setup viewpager adapter and indicator
        viewPagerAdapter = OnboardViewPagerAdapter(this)
        mSlideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager.addOnPageChangeListener(viewListener)
    }

    // Set up the indicator dots for the onboarding screens
    private fun setUpIndicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226")
                textSize = 35f
                setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            }
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            backBtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getItem(i: Int): Int = mSlideViewPager.currentItem + i
}















/*
package com.bardaval.ecommerce

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bardaval.ecommerce.activity.MainActivity

class OnboardingScreen : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var skipBtn: Button

    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)

        // Initialize UI elements
        backBtn = findViewById(R.id.backbtn)
        nextBtn = findViewById(R.id.nextbtn)
        skipBtn = findViewById(R.id.skipButton)

        backBtn.setOnClickListener {
            if (getItem(0) > 0) {
                mSlideViewPager.setCurrentItem(getItem(-1), true)
            }
        }

        nextBtn.setOnClickListener {
            if (getItem(0) < 3) {
                mSlideViewPager.setCurrentItem(getItem(1), true)
            } else {
                // Save onboarding completion status
                val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("OnboardingCompleted", true) // Mark onboarding as completed
                editor.apply()

                // Start MainActivity
                val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        skipBtn.setOnClickListener {
            // Save onboarding completion status when skipped
            val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("OnboardingCompleted", true) // Mark onboarding as completed
            editor.apply()

            // Start MainActivity
            val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        // Setup viewpager adapter and indicator
        viewPagerAdapter = ViewPagerAdapter(this)
        mSlideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager.addOnPageChangeListener(viewListener)
    }

    // Set up the indicator dots for the onboarding screens
    private fun setUpIndicator(position: Int) {
        dots = Array(4) { null }
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226")
                textSize = 35f
                setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            }
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            backBtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getItem(i: Int): Int = mSlideViewPager.currentItem + i
}


*/

























/*
package com.bardaval.ecommerce

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bardaval.ecommerce.activity.MainActivity
import com.bardaval.ecommerce.activity.Splashscreen

class OnboardingScreen : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var skipBtn: Button

    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)

        backBtn = findViewById(R.id.backbtn)
        nextBtn = findViewById(R.id.nextbtn)
        skipBtn = findViewById(R.id.skipButton)

        backBtn.setOnClickListener {
            if (getItem(0) > 0) {
                mSlideViewPager.setCurrentItem(getItem(-1), true)
            }
        }

        nextBtn.setOnClickListener {
            if (getItem(0) < 3) {
                mSlideViewPager.setCurrentItem(getItem(1), true)
            } else {
                // Save that onboarding has been completed
                val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("OnboardingCompleted", true)
                editor.apply()

                // Go to the splash screen
                val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        skipBtn.setOnClickListener {
            // Save that onboarding has been completed
            val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("OnboardingCompleted", true)
            editor.apply()

            // Go to the splash screen
            val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        viewPagerAdapter = ViewPagerAdapter(this)
        mSlideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager.addOnPageChangeListener(viewListener)
    }

    private fun setUpIndicator(position: Int) {
        dots = Array(4) { null }
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226")
                textSize = 35f
                setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            }
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            backBtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getItem(i: Int): Int = mSlideViewPager.currentItem + i
}



*/









/*package com.bardaval.ecommerce

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bardaval.ecommerce.activity.MainActivity

class OnboardingScreen : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var skipBtn: Button

    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)

        backBtn = findViewById(R.id.backbtn)
        nextBtn = findViewById(R.id.nextbtn)
        skipBtn = findViewById(R.id.skipButton)

        backBtn.setOnClickListener {
            if (getItem(0) > 0) {
                mSlideViewPager.setCurrentItem(getItem(-1), true)
            }
        }

        nextBtn.setOnClickListener {
            if (getItem(0) < 3) {
                mSlideViewPager.setCurrentItem(getItem(1), true)
            } else {
                val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        skipBtn.setOnClickListener {
            val intent = Intent(this@OnboardingScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        viewPagerAdapter = ViewPagerAdapter(this)
        mSlideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager.addOnPageChangeListener(viewListener)
    }

    private fun setUpIndicator(position: Int) {
        dots = Array(4) { null }
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226")
                textSize = 35f
                setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            }
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            backBtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getItem(i: Int): Int = mSlideViewPager.currentItem + i
}
*/