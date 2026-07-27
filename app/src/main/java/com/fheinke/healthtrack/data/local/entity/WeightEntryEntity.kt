package com.fheinke.healthtrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "weight_entries",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = HealthEntryEntity::class,
            parentColumns = ["entryId"],
            childColumns = ["entryId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ],
    indices = [androidx.room.Index(value = ["entryId"])]
)
data class WeightEntryEntity(
    @PrimaryKey
    val entryId: Long,

    val weightKg: Double,
    val bodyFatPercentage: Double?,
)
