package com.example.travelworld.domain.repository

import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip

interface TripRepository {
    suspend fun getTrips(): List<Trip>
    suspend fun addTrip(trip: Trip)
    suspend fun deleteTrip(tripId: Int)
    suspend fun updateTrip(trip: Trip)

    suspend fun getSubTripsForTrip(tripId: Int): List<SubTrip>
    suspend fun addSubTrip(subTrip: SubTrip)
    suspend fun deleteSubTrip(subTripId: Int)
    suspend fun updateSubTrip(subTrip: SubTrip)
}
