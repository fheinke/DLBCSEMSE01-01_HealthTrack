package com.fheinke.healthtrack.data.repository

import com.fheinke.healthtrack.data.local.dao.HealthEntryDao
import com.fheinke.healthtrack.data.local.entity.*
import com.fheinke.healthtrack.domain.model.HealthEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HealthEntryRepository(private val dao: HealthEntryDao) {
    suspend fun addHealthEntry(entry: HealthEntry): Long {
        return dao.insertHealthEntry(entry.toEntity())
    }

    fun getAllHealthEntries(userId: Long): Flow<List<HealthEntry>> {
        return dao.getAllHealthEntriesForUser(userId).map { it.map { entity -> entity.toDomain() } }
    }
}