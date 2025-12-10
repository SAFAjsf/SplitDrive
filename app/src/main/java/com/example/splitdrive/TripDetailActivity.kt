package com.example.splitdrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class TripDetailActivity : AppCompatActivity() {

    private var tripId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_detail)

        tripId = intent.getIntExtra("tripId", -1)

        val tvTripName = findViewById<TextView>(R.id.tv_trip_name)
        val tvTripDate = findViewById<TextView>(R.id.tv_trip_date)
        val btnAdd = findViewById<MaterialButton>(R.id.btn_add_expense)
        val btnBalance = findViewById<MaterialButton>(R.id.btn_view_balance)
        val rv = findViewById<RecyclerView>(R.id.rv_expenses)

        val trip = DataRepository.getTripById(tripId)

        if (trip != null) {
            tvTripName.text = trip.name
            tvTripDate.text = trip.fecha
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ExpenseAdapter(trip?.expenses ?: emptyList())

        btnAdd.setOnClickListener {
            val i = Intent(this, AddExpenseActivity::class.java)
            i.putExtra("tripId", tripId)
            startActivity(i)
        }

        btnBalance.setOnClickListener {
            val i = Intent(this, BalanceViewActivity::class.java)
            i.putExtra("tripId", tripId)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        val rv = findViewById<RecyclerView>(R.id.rv_expenses)
        val trip = DataRepository.getTripById(tripId)
        rv.adapter = ExpenseAdapter(trip?.expenses ?: emptyList())
    }
}
