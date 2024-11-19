package com.bardaval.ecommerce

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.bardaval.ecommerce.activity.MainActivity
import com.bardaval.ecommerce.sqlitedb.DatabaseHelper

class FeedbackFragment : Fragment() {

    private lateinit var myDb: DatabaseHelper
    private lateinit var editName: EditText
    private lateinit var editFeedback: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        myDb = DatabaseHelper(requireContext())

        editName = view.findViewById(R.id.editText_name)
        editFeedback = view.findViewById(R.id.editText_feedback)
        ratingBar = view.findViewById(R.id.ratingBar)
        btnSubmit = view.findViewById(R.id.button_submit)

        addData()

        // Handle back button press to navigate to MainActivity
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun addData() {
        btnSubmit.setOnClickListener {
            val name = editName.text.toString()
            val feedback = editFeedback.text.toString()
            val rating = ratingBar.rating.toInt()

            if (name.isEmpty() || feedback.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val isInserted = myDb.insertData(name, feedback, rating)
                if (isInserted) {
                    Toast.makeText(requireContext(), "Feedback saved successfully", Toast.LENGTH_LONG).show()
                    clearFields()
                } else {
                    Toast.makeText(requireContext(), "Failed to save feedback", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clearFields() {
        editName.setText("")
        editFeedback.setText("")
        ratingBar.rating = 0f
    }
}
