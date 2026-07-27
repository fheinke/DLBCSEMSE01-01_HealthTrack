package com.fheinke.healthtrack.domain.model

data class HealthEntry(
    val entryId: Long = 0,
    val userId: Long,
    val entryType: EntryType,
    val createdAt: Long = System.currentTimeMillis(),
    val source: DataSource,
    val note: String? = null
)