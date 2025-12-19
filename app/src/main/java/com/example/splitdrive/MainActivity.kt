package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import android.net.Uri


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

        findViewById<MaterialButton>(R.id.btn_open_maps).setOnClickListener {
            openMapsSearch("bencina cerca")
        }

        findViewById<MaterialButton>(R.id.btn_open_camera).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
    private fun openMapsSearch(text: String) {
        val query = Uri.encode(text)
        val geoUri = Uri.parse("geo:0,0?q=$query")
        val mapsIntent = Intent(Intent.ACTION_VIEW, geoUri).apply {
            setPackage("com.google.android.apps.maps")
        }

        if (mapsIntent.resolveActivity(packageManager) != null) {
            startActivity(mapsIntent)
        } else {
            // Fallback: abre en el navegador si Maps no está instalado
            val webUri = Uri.parse("https://www.google.com/maps/search/$query")
            startActivity(Intent(Intent.ACTION_VIEW, webUri))
        }}}