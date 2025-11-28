package com.example.splitdrive

data class Expense(val id: Int, val amount: Double, val category: String, val paidBy: String, val date: String)
data class Trip(val id: Int, val name: String, val date: String, val participants: List<String>, val expenses: MutableList<Expense> = mutableListOf())

object DataRepository {
    private var nextExpenseId = 1
    private val trips = mutableListOf<Trip>()

    init {
        trips.add(
            Trip(
                id = 1,
                name = "Salida a Valparaíso",
                date = "10 Nov 2025",
                participants = listOf("Ana", "Bruno", "Carla", "Javier"),
                expenses = mutableListOf(
                    Expense(id = nextExpenseId++, amount = 40000.0, category = "Gasolina", paidBy = "Ana", date = "10 Nov"),
                    Expense(id = nextExpenseId++, amount = 3000.0, category = "Peaje", paidBy = "Bruno", date = "10 Nov")
                )
            )
        )
    }

    fun getTrips(): List<Trip> = trips

    fun getTripById(id: Int): Trip? = trips.find { it.id == id }

    fun addExpense(tripId: Int, amount: Double, category: String, paidBy: String) {
        val trip = getTripById(tripId) ?: return
        val today = "Hoy"
        trip.expenses.add(Expense(id = nextExpenseId++, amount = amount, category = category, paidBy = paidBy, date = today))
    }

    fun calculateBalance(tripId: Int): Map<String, Double> {
        val trip = getTripById(tripId) ?: return emptyMap()
        val participants = trip.participants
        val total = trip.expenses.sumOf { it.amount }
        val share = if (participants.isNotEmpty()) total / participants.size else 0.0
        val paidByMap = participants.associateWith { p -> trip.expenses.filter { it.paidBy == p }.sumOf { it.amount } }
        return participants.associateWith { p -> (paidByMap[p] ?: 0.0) - share }
    }
}
