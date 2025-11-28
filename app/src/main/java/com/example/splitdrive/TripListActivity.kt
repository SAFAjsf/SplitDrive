package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class TripListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_list)

        findViewById<MaterialButton>(R.id.btn_trip_example).setOnClickListener {
            startActivity(Intent(this, TripDetailActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_empty_state).setOnClickListener {
            startActivity(Intent(this, EmptyStateActivity::class.java))
        }
    }
}
