package com.fheinke.healthtrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fheinke.healthtrack.domain.model.*

@Entity(tableName = "health_entries")
data class HealthEntryEntity (
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0,
    val userId: Long,
    val entryType: EntryType,
    val createdAt: Long = System.currentTimeMillis(),
    val source: DataSource,
    val note: String? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING_SYNC
)

fun HealthEntryEntity.toDomain() = HealthEntry(
    entryId = entryId,
    userId = userId,
    entryType = entryType,
    createdAt = createdAt,
    source = source,
    note = note
)

fun HealthEntry.toEntity() = HealthEntryEntity(
    entryId = entryId,
    userId = userId,
    entryType = entryType,
    createdAt = createdAt,
    source = source,
    note = note
)