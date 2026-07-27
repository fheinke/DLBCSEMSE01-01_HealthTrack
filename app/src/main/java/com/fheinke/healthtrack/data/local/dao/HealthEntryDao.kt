package com.fheinke.healthtrack.data.local.dao

import com.fheinke.healthtrack.data.local.entity.*

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.fheinke.healthtrack.domain.model.EntryType

@Dao
interface HealthEntryDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertHealthEntry(healthEntry: HealthEntryEntity): Long

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertActivityEntry(activityEntry: ActivityEntryEntity): Long

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertBloodPressureEntry(bloodPressureEntry: BloodPressureEntryEntity): Long

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertMoodEntry(moodEntry: MoodEntryEntity): Long

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertSleepEntry(sleepEntry: SleepEntryEntity): Long

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertWeightEntry(weightEntry: WeightEntryEntity): Long

    @Query("SELECT * FROM health_entries WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllHealthEntriesForUser(userId: Long): Flow<List<HealthEntryEntity>>

    @Query("SELECT * FROM health_entries WHERE userId = :userId AND entryType = :entryType ORDER BY createdAt DESC")
    fun getHealthEntriesByTypeForUser(userId: Long, entryType: EntryType): Flow<List<HealthEntryEntity>>
}