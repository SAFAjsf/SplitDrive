package com.example.splitdrive

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast // Necesario para mensajes de error
import androidx.appcompat.app.AppCompatActivity

class NewTripActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_trip) // Asegure que el layout es 'new_trip'

        // Corregido: Inicialización de Vistas
        val etOrigen = findViewById<EditText>(R.id.et_origen)
        val etDestino = findViewById<EditText>(R.id.et_destino)
        val etIntegrantes = findViewById<EditText>(R.id.et_integrantes)
        val btnCrear = findViewById<Button>(R.id.btn_crear_viaje)
        // Agregamos las vistas faltantes para 'name' y 'fecha' si existen en el layout
        // val etName = findViewById<EditText>(R.id.et_name)
        // val etFecha = findViewById<EditText>(R.id.et_fecha)

        btnCrear.setOnClickListener {

            val origen = etOrigen.text.toString().trim()
            val destino = etDestino.text.toString().trim()

            // Los siguientes campos son opcionales en el data class Trip (por defecto son "")
            val name = "" // O leer de una vista si existe (ej: etName.text.toString())
            val fecha = "" // O leer de una vista si existe (ej: etFecha.text.toString())

            val integrantes = etIntegrantes.text.toString().trim()
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            // CORRECCIÓN 1: Usar la variable 'origen' (minúscula) y manejar error de campos vacíos
            if (origen.isNotEmpty() && destino.isNotEmpty() && integrantes.isNotEmpty()) {

                // CORRECCIÓN 2: Usar 'Trip' con mayúscula.
                val nuevo = Trip(
                    origen = origen,
                    destino = destino,
                    integrantes = integrantes,
                    name = name,
                    fecha = fecha // Se pasan valores opcionales
                )

                // CORRECCIÓN 3: La función addTrip ya está en DataRepository.
                DataRepository.addTrip(nuevo)

                Toast.makeText(this, "Viaje creado con éxito.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Debe rellenar Origen, Destino e Integrantes.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}