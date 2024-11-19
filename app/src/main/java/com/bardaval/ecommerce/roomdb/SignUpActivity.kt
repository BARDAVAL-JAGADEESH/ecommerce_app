
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
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = AppDatabase.getDatabase(this)
        progressBar = findViewById(R.id.progress_bar)

        val signupButton: Button = findViewById(R.id.signup_button)
        signupButton.setOnClickListener {

            hideKeyboard(it)

            val fullName = findViewById<EditText>(R.id.full_name).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val mobile = findViewById<EditText>(R.id.mobile).text.toString()
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            progressBar.visibility = View.VISIBLE

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            val user = User(fullName = fullName, email = email, mobile = mobile, username = username, password = password)

            lifecycleScope.launch {
                try {
                    db.userDao().insertUser(user)
                    Toast.makeText(this@SignUpActivity, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@SignUpActivity, "Registration Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                } finally {
                    progressBar.visibility = View.GONE
                }
            }
        }

        val goToSignIn: TextView = findViewById(R.id.go_to_signin)
        goToSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}



















/*package com.bardaval.ecommerce.Sqlitedb // Update with your actual package name

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
import com.bardaval.ecommerce.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = DatabaseHelper(this)
        progressBar = findViewById(R.id.progress_bar)

        val signupButton: Button = findViewById(R.id.signup_button)
        signupButton.setOnClickListener {

            hideKeyboard(it)

            val fullName = findViewById<EditText>(R.id.full_name).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val mobile = findViewById<EditText>(R.id.mobile).text.toString()
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            progressBar.visibility = View.VISIBLE


            if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (db.insertUser(fullName, email, mobile, username, password)) {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SignInActivity::class.java))
                finish() // Close SignUpActivity
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
            }

            progressBar.visibility = View.GONE // Hide loading
        }

        val goToSignIn: TextView = findViewById(R.id.go_to_signin)
        goToSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}*/
