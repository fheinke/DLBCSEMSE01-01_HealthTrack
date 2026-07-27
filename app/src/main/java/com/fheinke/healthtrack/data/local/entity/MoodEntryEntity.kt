package com.fheinke.healthtrack.data.local.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(
    tableName = "mood_entries",
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
data class MoodEntryEntity(
    @PrimaryKey
    val entryId: Long,

    val moodScore: Int, // Integer Level from 1 to 5
    val stressLevel: Int, // Integer Level from 1 to 5
    val description: String? = null,
)
