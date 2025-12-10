package com.example.splitdrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splitdrive.R
// Se elimina la importación redundante o incorrecta 'com.example.splitdrive.trip'
// y se asume que la clase Data Class es 'Trip' con mayúscula.

class TripListAdapter(
    // CORRECCIÓN 1: Usar 'Trip' con mayúscula.
    private val trips: List<Trip>
) : RecyclerView.Adapter<TripListAdapter.TripViewHolder>() {

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Asumo que estos son los IDs correctos para el layout item_trip.
        val origenDestino: TextView = itemView.findViewById(R.id.txtOrigenDestino)
        val integrantes: TextView = itemView.findViewById(R.id.txtIntegrantes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val t = trips[position]
        // CORRECCIÓN 2: Acceso a las propiedades del data class 'Trip' (origen y destino).
        holder.origenDestino.text = "${t.origen} → ${t.destino}"
        // CORRECCIÓN 3: La propiedad se llama 'integrantes' y es una lista.
        holder.integrantes.text = "Integrantes: ${t.integrantes.joinToString(", ")}"
    }

    override fun getItemCount(): Int = trips.size
}