package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.btn_new_trip).setOnClickListener {
            startActivity(Intent(this, NewTripActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_open_trips).setOnClickListener {
            startActivity(Intent(this, TripListActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}

