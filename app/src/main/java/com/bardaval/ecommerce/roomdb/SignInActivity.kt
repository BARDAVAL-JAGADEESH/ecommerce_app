package com.bardaval.ecommerce.roomdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.activity.MainActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialize the database and progress bar
        db = AppDatabase.getDatabase(this)
        progressBar = findViewById(R.id.progress_bar)

        // Get references to UI elements
        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val signinButton: Button = findViewById(R.id.signin_button)
        val goToSignup: TextView = findViewById(R.id.go_to_signup)

        // Set onClickListener for the Sign In button
        signinButton.setOnClickListener {
            hideKeyboard(it) // Hide the keyboard when the button is clicked

            // Trim spaces from the input fields
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Show the progress bar while checking credentials
            progressBar.visibility = View.VISIBLE

            // Launch a coroutine to check credentials in the background
            lifecycleScope.launch {
                val user = db.userDao().getUser(username, password)

                if (user != null) {
                    // If the user exists, show success message
                    Toast.makeText(this@SignInActivity, "Sign In Successful", Toast.LENGTH_SHORT).show()

                    // Start MainActivity and delay hiding the progress bar until it starts
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finish the current activity to remove it from the back stack
                } else {
                    // If credentials are invalid, show error message
                    Toast.makeText(this@SignInActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()

                    // Hide the progress bar on failure
                    progressBar.visibility = View.GONE
                }
            }
        }

        // Set onClickListener for the "Go to Signup" text view
        goToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // Helper function to hide the keyboard
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}





/*package com.bardaval.ecommerce.Sqlitedb
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Context // Add this import
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.activity.MainActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        db = DatabaseHelper(this)
        progressBar = findViewById(R.id.progress_bar)

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val signinButton: Button = findViewById(R.id.signin_button)
        val goToSignup: TextView = findViewById(R.id.go_to_signup)

        signinButton.setOnClickListener {
            // Hide the keyboard when the button is clicked
            hideKeyboard(it)

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            progressBar.visibility = View.VISIBLE // Show loading

            // Perform sign-in operation
            if (db.checkUser(username, password)) {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish() // Close SignInActivity
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }

            progressBar.visibility = View.GONE // Hide loading
        }

        goToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // Function to hide the keyboard
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
*/