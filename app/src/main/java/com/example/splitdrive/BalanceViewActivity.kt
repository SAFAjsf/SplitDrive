package com.example.splitdrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class BalanceViewActivity : AppCompatActivity() {

    private var tripId: Int = -1

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
        setContentView(R.layout.activity_balance_view)

        tripId = intent.getIntExtra("tripId", 1)

        val tvAna = findViewById<TextView>(R.id.tv_ana)
        val tvBruno = findViewById<TextView>(R.id.tv_bruno)
        val tvCarla = findViewById<TextView>(R.id.tv_carla)
        val tvJavier = findViewById<TextView>(R.id.tv_javier)
        val btnSettle = findViewById<MaterialButton>(R.id.btn_settle)

        // Usamos DataRepository.calculateBalance (ahora pública y devuelve Map)
        val balances = DataRepository.calculateBalance(tripId)

        // Función auxiliar para formatear valores absolutos
        fun fmt(v: Double) = "%.0f".format(kotlin.math.abs(v))

        // El acceso a Map es correcto. Las comprobaciones de nullidad se realizan con ?: 0.0
        tvAna.text = if ((balances["Ana"] ?: 0.0) >= 0) "Ana debe recibir ${fmt(balances["Ana"]!!)} CLP" else "Ana debe pagar ${fmt(balances["Ana"]!!)} CLP"
        tvBruno.text = if ((balances["Bruno"] ?: 0.0) >= 0) "Bruno debe recibir ${fmt(balances["Bruno"]!!)} CLP" else "Bruno debe pagar ${fmt(balances["Bruno"]!!)} CLP"
        tvCarla.text = if ((balances["Carla"] ?: 0.0) >= 0) "Carla debe recibir ${fmt(balances["Carla"]!!)} CLP" else "Carla debe pagar ${fmt(balances["Carla"]!!)} CLP"
        tvJavier.text = if ((balances["Javier"] ?: 0.0) >= 0) "Javier debe recibir ${fmt(balances["Javier"]!!)} CLP" else "Javier debe pagar ${fmt(balances["Javier"]!!)} CLP"

        btnSettle.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Marcar como saldado?")
                .setPositiveButton("Sí") { _, _ ->
                    finish()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }
}

// Se elimina la función de extensión stub, ahora está en DataRepository.kt