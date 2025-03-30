package com.example.travelworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.local.entity.SubTripEntity
import com.example.travelworld.data.local.entity.TripEntity


@Database(
    entities = [TripEntity::class, SubTripEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun subTripDao(): SubTripDao
}