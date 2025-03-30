package com.example.travelworld.domain.model

// Trip.kt
data class Trip(
    val id: Int = 0,
    val title: String,
    val description: String,
    val subTrips: List<SubTrip> = emptyList()
)
