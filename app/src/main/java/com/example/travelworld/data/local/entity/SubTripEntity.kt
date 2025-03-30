package com.example.travelworld.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "subtrips",
    foreignKeys = [ForeignKey(
        entity = TripEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentTripId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubTripEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentTripId: Int,
    val title: String,
    val description: String
)