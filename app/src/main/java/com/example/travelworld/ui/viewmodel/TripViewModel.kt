package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    // Estado mutable para mantener el listado de tareas
    private val _trips = mutableStateListOf<Trip>()
    val trips: List<Trip> get() = _trips

    init {
        loadTripsOld()
    }

    private fun loadTripsOld() {
        _trips.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _trips.addAll(repository.getTrips())
        }
    }

    private fun loadTrips() {
        viewModelScope.launch {
            // Fetch the trips on IO thread
            val tripsFromDb = withContext(Dispatchers.IO) { repository.getTrips() }
            // Update state on main thread
            _trips.clear()
            _trips.addAll(tripsFromDb)
        }
    }

    fun addTrip(trip: Trip) {
        viewModelScope.launch {
            repository.addTrip(trip)
            loadTrips()
        }
    }

    fun deleteTrip(tripId: Int) {
        viewModelScope.launch {
            repository.deleteTrip(tripId)
            loadTrips()
        }
    }

    fun updateTrip(trip: Trip) {
        viewModelScope.launch {
            repository.updateTrip(trip)
            loadTrips()
        }
    }
}