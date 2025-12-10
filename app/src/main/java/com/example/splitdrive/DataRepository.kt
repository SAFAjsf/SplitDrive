package com.example.splitdrive

// Hacemos que la clase DataRepository sea un singleton object para un fácil acceso
object DataRepository {
    // Lista temporal para simular la base de datos de viajes (Solo para desarrollo)
    // Asegurarse que se usa 'Trip' con Mayúscula
    private val tripStore = mutableListOf(
        Trip(
            id = 1,
            origen = "Origen Ejemplo",
            destino = "Destino Ejemplo",
            integrantes = listOf("Ana", "Bruno", "Carla", "Javier"),
            name = "Viaje de Prueba",
            expenses = mutableListOf()
        )
    )

    fun getTripById(tripId: Int): Trip? {
        return tripStore.find { it.id == tripId }
    }

    fun addExpense(
        tripId: Int,
        amount: Double,
        category: String,
        paidBy: String
    ) {
        // ... (el código ya estaba correcto, asumiendo que Expense está definido)
        val trip = getTripById(tripId)
        trip?.let {
            val newExpense = Expense(
                title = category,
                amount = amount,
                category = category,
                paidBy = paidBy,
                date = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())
            )
            it.expenses.add(newExpense)
        }
    }

    fun calculateBalance(tripId: Int): Map<String, Double> {
        // ... (el código ya estaba correcto)
        val trip = getTripById(tripId)
        val balances = trip?.integrantes?.associateWith { 0.0 }?.toMutableMap() ?: mutableMapOf()

        if (tripId == 1) {
            balances["Ana"] = 500.0
            balances["Bruno"] = -250.0
            balances["Carla"] = 100.0
            balances["Javier"] = -350.0
        }

        return balances
    }

    // Función para obtener todos los viajes (Ya estaba correcta, asegurando el tipo 'Trip')
    fun getTrips(): List<Trip> {
        return tripStore
    }

    // Función para añadir un viaje (Ya estaba correcta, asegurando el tipo 'Trip')
    fun addTrip(newTrip: Trip) {
        val nextId = tripStore.maxOfOrNull { it.id } ?: 0
        val tripWithId = newTrip.copy(id = nextId + 1)
        tripStore.add(tripWithId)
    }
}