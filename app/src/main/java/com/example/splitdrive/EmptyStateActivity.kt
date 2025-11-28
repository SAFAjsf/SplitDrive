package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class EmptyStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_state)

        findViewById<MaterialButton>(R.id.btn_go_back).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btn_simulate_error).setOnClickListener {
            startActivity(Intent(this, ErrorStateActivity::class.java))
        }
    }
}
