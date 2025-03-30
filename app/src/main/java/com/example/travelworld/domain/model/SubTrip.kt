package com.example.travelworld.domain.model

// Subtrip.kt
data class SubTrip(
    val id: Int = 0,
    val parentTripId: Int,
    val title: String,
    val description: String,
)
