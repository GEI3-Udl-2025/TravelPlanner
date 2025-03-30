package com.example.travelworld.data.mapper

import com.example.travelworld.data.local.entity.SubTripEntity
import com.example.travelworld.data.local.entity.TripEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip


// De dominio a entidad
fun Trip.toEntity(): TripEntity =
    TripEntity(id = id, title = title, description = description)

fun SubTrip.toEntity(): SubTripEntity =
    SubTripEntity(id = id, parentTripId = parentTripId, title = title, description = description)

// De entidad a dominio
fun TripEntity.toDomain(subTrips: List<SubTrip>): Trip =
    Trip(id = id, title = title, description = description, subTrips = subTrips)

fun SubTripEntity.toDomain(): SubTrip =
    SubTrip(id = id, parentTripId = parentTripId, title = title, description = description)