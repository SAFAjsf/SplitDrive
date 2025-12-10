package com.example.splitdrive

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


// Se elimina la propiedad de extensión incorrecta: private val Unit.participants: Any
// Los accesos a DataRepository se hacen directamente usando el objeto DataRepository

class AddExpenseActivity : AppCompatActivity() {

    var tripId: Int = -1

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            // Asumo que tienes una actividad LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        tripId = intent.getIntExtra("tripId", -1)

        val etAmount = findViewById<TextInputEditText>(R.id.et_amount)
        val etCategory = findViewById<TextInputEditText>(R.id.et_category)
        val spinner = findViewById<Spinner>(R.id.spinner_paidby)
        val btnSave = findViewById<MaterialButton>(R.id.btn_save_expense)

        // Usamos DataRepository.getTripById (ahora pública)
        val trip = DataRepository.getTripById(tripId)

        // Corregido: Si trip es null, usamos la lista de Ana/Bruno, sino usamos los integrantes del viaje
        val participants: List<String> = trip?.integrantes.orEmpty().ifEmpty {
            listOf("Ana", "Bruno", "Carla", "Javier") // Lista por defecto si no hay viaje/integrantes
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            participants
        )

        spinner.adapter = adapter

        btnSave.setOnClickListener {
            val amountText = etAmount.text?.toString()?.trim().orEmpty()
            val category = etCategory.text?.toString()?.trim().orEmpty()
            val paidBy = spinner.selectedItem?.toString().orEmpty()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "Ingresa el monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (category.isEmpty()) {
                Toast.makeText(this, "Ingresa la categoría", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamada a DataRepository.addExpense (ahora pública)
            DataRepository.addExpense(tripId, amount, category, paidBy)

            Toast.makeText(this, "Gasto registrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

// Se eliminan las funciones de extensión stub, ahora están en DataRepository.kt