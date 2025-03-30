package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubTripViewModel @Inject constructor(
    private val repository: TripRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // Se espera que "tripId" sea pasado en la navegación
    val tripId: Int = savedStateHandle["tripId"] ?: 0

    private val _subTrips = mutableStateListOf<SubTrip>()
    val subTrips: List<SubTrip> get() = _subTrips

    init {
        loadSubTrips()
    }

    private fun loadSubTrips() {
        _subTrips.clear()
        viewModelScope.launch {
            _subTrips.addAll(repository.getSubTripsForTrip(tripId))
        }
    }

    fun addSubTrip(subTrip: SubTrip) {
        viewModelScope.launch {
            repository.addSubTrip(subTrip)
            loadSubTrips()
        }
    }

    fun deleteSubTrip(subTripId: Int) {
        viewModelScope.launch {
            repository.deleteSubTrip(subTripId)
            loadSubTrips()
        }
    }

    fun updateSubTrip(updatedSubTrip: SubTrip) {
        viewModelScope.launch {
            repository.updateSubTrip(updatedSubTrip)
            loadSubTrips()
        }
    }

}