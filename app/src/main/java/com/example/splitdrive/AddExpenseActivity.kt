package com.example.splitdrive

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddExpenseActivity : AppCompatActivity() {

    private var tripId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Recibir ID del viaje
        tripId = intent.getIntExtra("tripId", -1)

        val etAmount = findViewById<TextInputEditText>(R.id.et_amount)
        val etCategory = findViewById<TextInputEditText>(R.id.et_category)
        val spinner = findViewById<Spinner>(R.id.spinner_paidby)
        val btnSave = findViewById<MaterialButton>(R.id.btn_save_expense)

        // Obtener información del viaje
        val trip = DataRepository.getTripById(tripId)

        // Si no existen participantes, usar fallback
        val participants = trip?.participants?.ifEmpty { null }
            ?: listOf("Ana", "Bruno")

        // Adaptador del Spinner utilizando un layout más compatible con Material
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            participants
        )

        spinner.adapter = adapter

        // Acción al presionar GUARDAR
        btnSave.setOnClickListener {
            val amountText = etAmount.text?.toString()?.trim().orEmpty()
            val category = etCategory.text?.toString()?.trim().orEmpty()
            val paidBy = spinner.selectedItem?.toString().orEmpty()

            // Validaciones
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

            // Registrar el gasto en el repositorio
            DataRepository.addExpense(tripId, amount, category, paidBy)

            Toast.makeText(this, "Gasto registrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
