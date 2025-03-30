package com.example.travelworld.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.travelworld.data.local.entity.SubTripEntity

@Dao
interface SubTripDao {
    @Query("SELECT * FROM subtrips WHERE parentTripId = :tripId")
    suspend fun getSubTripsForTrip(tripId: Int): List<SubTripEntity>

    @Insert
    suspend fun addSubTrip(subTrip: SubTripEntity): Long

    @Query("DELETE FROM subtrips WHERE id = :subTripId")
    suspend fun deleteSubTrip(subTripId: Int)

    @Update
    suspend fun updateSubTrip(subTrip: SubTripEntity)
}