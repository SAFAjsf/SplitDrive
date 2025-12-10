package com.example.splitdrive

data class Trip(
    val id: Int = 0,
    val origen: String,
    val destino: String,
    val integrantes: List<String>,
    val fecha: String = "",
    val name: String = "",
    val expenses: MutableList<Expense> = mutableListOf()
)

data class Expense(
    val title: String,
    val amount: Double, // Corregido a Double
    val category: String, // ¡AGREGADO!
    val paidBy: String,   // ¡AGREGADO!
    val date: String      // ¡AGREGADO!
)