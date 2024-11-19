
package com.bardaval.ecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.AboutFragment
import com.bardaval.ecommerce.BlankFragment
import com.bardaval.ecommerce.CartDataFragment
import com.bardaval.ecommerce.FABFragment
import com.bardaval.ecommerce.FeedbackFragment
import com.bardaval.ecommerce.sqlitedb.FeedbackListActivity
import com.bardaval.ecommerce.HomeFragment
import com.bardaval.ecommerce.LikeFragment
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.CartItemsFragment
import com.bardaval.ecommerce.roomdb.SignInActivity
import com.bardaval.ecommerce.roomdb.SignUpActivity
import com.bardaval.ecommerce.items.ListofItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle = findViewById(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.recent -> {
                    replaceFragment(CartItemsFragment())
                    true
                }
                R.id.items -> {
                    replaceFragment(LikeFragment())
                    true
                }
                R.id.feedback -> {
                    replaceFragment(FeedbackFragment())
                    true
                }
                else -> false
            }
        }

        val customMenuIcon: ImageView = findViewById(R.id.custom_menu_icon)
        customMenuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val customNotificationIcon: ImageView = findViewById(R.id.userprofile)
        customNotificationIcon.setOnClickListener {
            showProfilePopupMenu(customNotificationIcon)
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }
    private fun showProfilePopupMenu(view: View) {
        // Create an AlertDialog instead of a PopupMenu
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Account Options")
            .setItems(arrayOf("Login", "Create New Account?", "Logout")) { dialog, which ->
                when (which) {
                    0 -> {
                        // "Login" clicked - Open SignInActivity
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        // "Create New Account" clicked - Open SignUpActivity
                        val intent = Intent(this, SignUpActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        // "Logout" clicked - Handle logout
                        logoutUser()
                    }
                }
                dialog.dismiss()  // Close the dialog after an option is selected
            }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                replaceFragment(AboutFragment())
            }
            R.id.blank -> {
                replaceFragment(BlankFragment())
            }
            R.id.recent -> {
                replaceFragment(CartItemsFragment())
            }
            R.id.Cartfab -> {
                replaceFragment(FABFragment())
            }
            R.id.cartdata -> {
                replaceFragment(CartDataFragment())
            }

            R.id.flipslide -> {
                val intent = Intent(this, FashionActivity::class.java)
                startActivity(intent)
            }
            R.id.itemapi -> {
                val intent = Intent(this, ListofItems::class.java)
                startActivity(intent)
            }
            R.id.feedactivity -> {
               val intent=Intent(this, FeedbackListActivity::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                logoutUser() // Handle logout with confirmation dialog
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
        toolbarTitle.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Show the toolbar only for HomeFragment
        toolbar.visibility = if (fragment is HomeFragment) View.VISIBLE else View.GONE
    }

    private fun logoutUser() {
        // Create an AlertDialog to confirm logout
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")

        // Set up the Yes button
        builder.setPositiveButton("Yes") { dialog, _ ->
            // Clear user session data here (e.g., SharedPreferences)
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Redirect to the SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        // Set up the No button
        builder.setNegativeButton("No") { dialog, _ ->
            // Just dismiss the dialog if the user presses No
            dialog.dismiss()
        }

        // Create the dialog
        val alertDialog = builder.create()

        // Show the dialog first, so we can access the buttons and style them
        alertDialog.show()

        // Set the button text colors to white
        alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(android.R.color.white))
        alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(android.R.color.white))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}






/*package com.bardaval.ecommerce.activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.FeedbackFragment
import com.bardaval.ecommerce.activity.FlipperActivity
import com.bardaval.ecommerce.HomeFragment
import com.bardaval.ecommerce.ItemFragment
import com.bardaval.ecommerce.ProfileFragment
import com.bardaval.ecommerce.activity.Profileactivity
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.RecentFragment
import com.bardaval.ecommerce.feedback.FeedbackActivity
import com.bardaval.ecommerce.items.ListofItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle = findViewById(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.recent -> {
                    replaceFragment(RecentFragment())
                    true
                }
                R.id.items -> {
                    replaceFragment(ItemFragment())
                    true
                }
                R.id.feedback -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        val customMenuIcon: ImageView = findViewById(R.id.custom_menu_icon)
        customMenuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val customNotificationIcon: ImageView = findViewById(R.id.userprofile)
        customNotificationIcon.setOnClickListener {
            val intent = Intent(this,Profileactivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                replaceFragment(HomeFragment())
            }
            R.id.recent -> {
                replaceFragment(RecentFragment())
            }

            R.id.flipslide -> {
                val intent = Intent(this, FlipperActivity::class.java)
                startActivity(intent)
            }
            R.id.itemapi -> {
                val intent = Intent(this, ListofItems::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                logoutUser() // Handle logout with confirmation dialog
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
        toolbarTitle.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Show the toolbar only for HomeFragment
        toolbar.visibility = if (fragment is HomeFragment) View.VISIBLE else View.GONE
    }

    private fun logoutUser() {
        // Create an AlertDialog to confirm logout
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")

        // Set up the Yes button
        builder.setPositiveButton("Yes") { dialog, _ ->
            // Clear user session data here (e.g., SharedPreferences)
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Redirect to the SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        // Set up the No button
        builder.setNegativeButton("No") { dialog, _ ->
            // Just dismiss the dialog if the user presses No
            dialog.dismiss()
        }

        // Create the dialog
        val alertDialog = builder.create()

        // Show the dialog first, so we can access the buttons and style them
        alertDialog.show()

        // Set the button text colors to white
        alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(android.R.color.white))
        alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(android.R.color.white))
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}




*/






















/*package com.bardaval.ecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.FeedbackFragment
import com.bardaval.ecommerce.activity.FlipperActivity
import com.bardaval.ecommerce.HomeFragment
import com.bardaval.ecommerce.ItemFragment
import com.bardaval.ecommerce.R
import com.bardaval.ecommerce.RecentFragment
import com.bardaval.ecommerce.items.ListofItems
import com.bardaval.ecommerce.notification.NotificationListActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle = findViewById(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.recent -> {
                    replaceFragment(RecentFragment())
                    true
                }
                R.id.items -> {
                    replaceFragment(ItemFragment())
                    true
                }
                R.id.feedback -> {
                    replaceFragment(FeedbackFragment())
                    true
                }
                else -> false
            }
        }

        val customMenuIcon: ImageView = findViewById(R.id.custom_menu_icon)
        customMenuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val customNotificationIcon: ImageView = findViewById(R.id.notification_icon)
        customNotificationIcon.setOnClickListener {
            val intent = Intent(this, NotificationListActivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                replaceFragment(HomeFragment())
            }
            R.id.recent -> {
                replaceFragment(RecentFragment())
            }

            R.id.flipslide -> {

                val intent = Intent(this, FlipperActivity::class.java)
                startActivity(intent)
            }
            R.id.itemapi -> {

                val intent = Intent(this, ListofItems::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                logoutUser() // Handle logout
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
        toolbarTitle.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Show the toolbar only for HomeFragment
        toolbar.visibility = if (fragment is HomeFragment) View.VISIBLE else View.GONE
    }

    private fun logoutUser() {
        // Clear user session data here (e.g., SharedPreferences)
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()


        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
*/