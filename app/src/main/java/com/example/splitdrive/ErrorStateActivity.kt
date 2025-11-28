package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class ErrorStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_state)

        findViewById<MaterialButton>(R.id.btn_retry).setOnClickListener {
            finish()
        }
    }
}
