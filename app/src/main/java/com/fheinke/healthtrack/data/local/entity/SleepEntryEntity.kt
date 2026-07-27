package com.fheinke.healthtrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sleep_entries",
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
data class SleepEntryEntity (
    @PrimaryKey
    val entryId: Long,

    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long = System.currentTimeMillis(),
    val quality: Int, // Sleep quality rating (e.g., 1-5)
)
