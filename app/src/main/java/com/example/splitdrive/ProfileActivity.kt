package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        findViewById<MaterialButton>(R.id.btn_sign_out).setOnClickListener {
            finish()
        }
    }
}
