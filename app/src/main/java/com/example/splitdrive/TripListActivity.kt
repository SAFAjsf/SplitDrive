package com.example.splitdrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton // Asumo que se necesita para añadir viaje
import android.content.Intent // Necesario para el Intent

class TripListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TripListAdapter
    // Agregamos el FAB para la navegación si existe en el layout
    // private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_list)

        // CORRECCIÓN: Asumiendo que el ID del layout es 'rvTrips' (era un error de referencia)
        recyclerView = findViewById(R.id.rv_trips)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el botón FAB si existe en el layout
        /*
        fabAdd = findViewById<FloatingActionButton>(R.id.fab_add_trip)
        fabAdd.setOnClickListener {
            startActivity(Intent(this, NewTripActivity::class.java))
        }
        */
    }

    override fun onResume() {
        super.onResume()

        val lista = DataRepository.getTrips()

        // El adaptador usa la clase 'Trip' mayúscula.
        adapter = TripListAdapter(lista)
        recyclerView.adapter = adapter

        // Notificamos al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged()
    }
}