package com.bardaval.ecommerce

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.activity.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AboutFragment : Fragment(), OnMapReadyCallback {

    private lateinit var youtubeId: ImageView
    private lateinit var instaId: ImageView
    private lateinit var twitterId: ImageView
    private lateinit var backButton: ImageView
    private lateinit var googleMap: GoogleMap
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // Initialize views
        youtubeId = view.findViewById(R.id.youtube_id)
        instaId = view.findViewById(R.id.insta_id)
        twitterId = view.findViewById(R.id.twiter_id)
        latitudeTextView = view.findViewById(R.id.latitudeTextView)
        longitudeTextView = view.findViewById(R.id.longitudeTextView)
        backButton = view.findViewById(R.id.back_button)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Handle back button press
        handlePhysicalBackButton()

        // Set up social media click listeners
        youtubeId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCzLGchsyCR0p8YDrsWIC-uw"))
            startActivity(intent)
        }

        instaId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/jagadeesh_0078?igsh=MWh2enhlODRta2lqbQ=="))
            startActivity(intent)
        }

        twitterId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/username"))
            startActivity(intent)
        }

        // Back button click listener
        backButton.setOnClickListener {
            navigateToMainActivity()
        }

        // Initialize Google Map
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Show location permission alert dialog
        showLocationAlertDialog()

        return view
    }

    private fun handlePhysicalBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMainActivity()
            }
        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // No default marker or camera position is set now.
    }

    private fun showLocationAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Location Access")
        builder.setMessage("Allow app to access your current location?")
        builder.setPositiveButton("Allow") { _, _ ->
            // Check and request location permission
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Request permissions
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            } else {
                // Permission granted, get current location
                getCurrentLocation()
            }
        }
        builder.setNegativeButton("Deny") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Location access denied", Toast.LENGTH_SHORT).show()
        }



        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

        // Customize the buttons' text color (optional styling)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(android.R.color.white,null))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(resources.getColor(android.R.color.white,null))
    }

    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                googleMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))

                latitudeTextView.text = "Latitude: ${location.latitude}"
                longitudeTextView.text = "Longitude: ${location.longitude}"
                Toast.makeText(requireContext(), "Current location: $location", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}








/*package com.bardaval.ecommerce

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.activity.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AboutFragment : Fragment(), OnMapReadyCallback {

    private lateinit var youtubeId: ImageView
    private lateinit var instaId: ImageView
    private lateinit var twitterId: ImageView
    private lateinit var backButton: ImageView
    private lateinit var googleMap: GoogleMap
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // Initialize views
        youtubeId = view.findViewById(R.id.youtube_id)
        instaId = view.findViewById(R.id.insta_id)
        twitterId = view.findViewById(R.id.twiter_id)
        latitudeTextView = view.findViewById(R.id.latitudeTextView)
        longitudeTextView = view.findViewById(R.id.longitudeTextView)
        backButton = view.findViewById(R.id.back_button)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Set up social media click listeners
        youtubeId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCzLGchsyCR0p8YDrsWIC-uw"))
            startActivity(intent)
        }

        instaId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/jagadeesh_0078?igsh=MWh2enhlODRta2lqbQ=="))
            startActivity(intent)
        }

        twitterId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/username"))
            startActivity(intent)
        }

        // Back button click listener to navigate to MainActivity
        backButton.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Optional: to close the current fragment and activity, so user can't go back to this screen using the back button
        }

        // Initialize Google Map
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Directly show location permission alert dialog
        showLocationAlertDialog()

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // No default marker or camera position is set now.
    }

    private fun showLocationAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Location Access")
        builder.setMessage("Allow app to access your current location?")
        builder.setPositiveButton("Allow") { _, _ ->
            // Check and request location permission
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Request permissions
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            } else {
                // Permission granted, get current location
                getCurrentLocation()
            }
        }
        builder.setNegativeButton("Deny") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Location access denied", Toast.LENGTH_SHORT).show()
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

        // Customize the buttons' text color (optional styling)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(android.R.color.white,null))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(resources.getColor(android.R.color.white,null))
    }

    // Function to get current location
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                // Show the current location on the map
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                googleMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))

                // Display latitude and longitude in the text views
                latitudeTextView.text = "Latitude: ${location.latitude}"
                longitudeTextView.text = "Longitude: ${location.longitude}"
                Toast.makeText(requireContext(), "Current location: $location", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
*/