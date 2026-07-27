package com.fheinke.healthtrack.data.local.entity

import androidx.room.PrimaryKey
import androidx.room.Entity
@Entity(
    tableName = "blood_pressure_entries",
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
data class BloodPressureEntryEntity(
    @PrimaryKey
    val entryId: Long,

    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int?,
)
