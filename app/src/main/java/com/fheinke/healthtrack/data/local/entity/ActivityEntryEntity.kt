package com.fheinke.healthtrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fheinke.healthtrack.domain.model.ActivityType

@Entity(
    tableName = "activity_entries",
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
data class ActivityEntryEntity(
    @PrimaryKey
    val entryId: Long,

    val activityType: ActivityType,
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long = System.currentTimeMillis(),
    val distanceMeters: Double?,
    val caloriesBurned: Int?,
    val averageSpeedKmh: Double?
)
