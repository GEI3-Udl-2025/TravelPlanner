package com.example.travelworld.data

import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.mapper.toDomain
import com.example.travelworld.data.mapper.toEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repository.TripRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val subTripDao: SubTripDao
) : TripRepository {

    // Listas mutables para almacenar datos en memoria
    //private val trips = mutableListOf<Trip>()
    //private val subTrips = mutableListOf<SubTrip>()

    override suspend fun getTrips(): List<Trip> {
        val tripEntities = tripDao.getTrips()
        // Podríamos obtener todas las subtrips de golpe para optimizar
        // y luego agruparlas, pero para el ejemplo haremos una consulta por tarea.
        return tripEntities.map { tripEntity ->
            val subs = subTripDao.getSubTripsForTrip(tripEntity.id).map { it.toDomain() }
            tripEntity.toDomain(subs)
        }
    }

    override suspend  fun addTrip(trip: Trip) {
        // Insertar la tarea en la DB
        tripDao.addTrip(trip.toEntity())
        // SubTrips se gestionan por separado si fuera necesario
    }

    override suspend  fun deleteTrip(tripId: Int) {
        tripDao.deleteTrip(tripId)
        // Por la ForeignKey con onDelete = CASCADE,
        // las subtareas también se borran automáticamente
    }

    override suspend  fun updateTrip(trip: Trip) {
        tripDao.updateTrip(trip.toEntity())
    }

    override suspend  fun getSubTripsForTrip(tripId: Int): List<SubTrip> {
        return subTripDao.getSubTripsForTrip(tripId).map { it.toDomain() }
    }

    override suspend fun addSubTrip(subTrip: SubTrip) {
        subTripDao.addSubTrip(subTrip.toEntity())
    }

    override suspend fun deleteSubTrip(subTripId: Int) {
        subTripDao.deleteSubTrip(subTripId)
    }

    override suspend fun updateSubTrip(subTrip: SubTrip) {
        subTripDao.updateSubTrip(subTrip.toEntity())
    }
}