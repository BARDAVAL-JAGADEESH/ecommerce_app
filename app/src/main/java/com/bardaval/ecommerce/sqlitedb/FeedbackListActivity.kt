package com.bardaval.ecommerce.sqlitedb

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bardaval.ecommerce.R

class FeedbackListActivity : AppCompatActivity() {

    private lateinit var myDb: DatabaseHelper
    private lateinit var listView: ListView
    private lateinit var feedbackList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_list)

        myDb = DatabaseHelper(this)

        listView = findViewById(R.id.listView_feedback)
        feedbackList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, feedbackList)
        listView.adapter = adapter

        loadFeedbackData()  // Load feedback when the activity is opened
    }

    private fun loadFeedbackData() {
        feedbackList.clear()
        val res: Cursor = myDb.getAllData()
        if (res.count == 0) {
            feedbackList.add("No feedback available")
            Toast.makeText(this, "No feedback found", Toast.LENGTH_LONG).show()
        } else {
            while (res.moveToNext()) {
                val data = "User: ${res.getString(1)}\nRating: ${res.getString(3)}\nFeedback: ${res.getString(2)}"
                feedbackList.add(data)
            }
        }
        adapter.notifyDataSetChanged()
    }
}
