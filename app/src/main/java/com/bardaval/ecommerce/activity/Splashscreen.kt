package com.bardaval.ecommerce.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.roomdb.SignInActivity

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        supportActionBar?.hide()

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        // Get SharedPreferences to check if onboarding has been completed
        val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("IsFirstLaunch", true)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (isFirstLaunch) {

                // Set IsFirstLaunch to false so that OnboardingScreen won't show again
                with(sharedPreferences.edit()) {
                    putBoolean("IsFirstLaunch", false)
                    apply()
                }
                Intent(this, OnboardingScreen::class.java)
            } else {
                // Otherwise, show MainActivity (onboarding already completed)
                Intent(this, SignInActivity::class.java)
            }
            startActivity(intent)
            finish() // Close the splash screen
        }, 2000) // Show splash screen for 2 seconds
    }
}













/*package com.bardaval.ecommerce.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.activity.OnboardingScreen
import com.bardaval.ecommerce.R

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        // Retrieve onboarding completion status from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
        val onboardingCompleted = sharedPreferences.getBoolean("OnboardingCompleted", false)

        // Show splash screen for 2 seconds, then decide which activity to launch
        Handler(Looper.getMainLooper()).postDelayed({
            if (onboardingCompleted) {
                // Onboarding has been completed, launch MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // Onboarding not completed, launch OnboardingScreen
                startActivity(Intent(this, OnboardingScreen::class.java))
            }
            finish() // Close the splash screen activity
        }, 2000) // 2000 milliseconds = 2 seconds delay
    }
}
*/




/*
package com.bardaval.ecommerce.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.activity.OnboardingScreen
import com.bardaval.ecommerce.R

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        // Check if onboarding has been completed
        val sharedPreferences: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
        val onboardingCompleted = sharedPreferences.getBoolean("OnboardingCompleted", false)

        // Delay to show splash screen for 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            if (onboardingCompleted) {
                // Onboarding is completed, start MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Onboarding not completed, start OnboardingScreen
                val intent = Intent(this, OnboardingScreen::class.java)
                startActivity(intent)
            }
            finish() // Finish splash screen activity
        }, 2000) // 2000 milliseconds = 2 seconds
    }
}



*/

















/*package com.bardaval.ecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.R

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen) // Ensure this matches your splash screen layout file name

        // Delay to show splash screen for 2 seconds
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // Start SignInActivity after the delay
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish the splash screen activity
        }, 2000) // 2000 milliseconds = 2 seconds
    }
}*/
