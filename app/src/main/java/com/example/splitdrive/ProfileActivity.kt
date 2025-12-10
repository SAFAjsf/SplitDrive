package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth // Importar FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance() // Inicializar auth

        findViewById<MaterialButton>(R.id.btn_sign_out).setOnClickListener {

            // 1. CERRAR SESIÓN DE FIREBASE
            auth.signOut()

            // 2. NAVEGAR A LOGIN y LIMPIAR LA PILA DE ACTIVIDADES
            val intent = Intent(this, LoginActivity::class.java)
            // Esto asegura que al presionar 'Atrás' desde Login, la aplicación se cierre.
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // 3. FINALIZAR LA ACTIVIDAD ACTUAL
            finish()
        }
    }
}