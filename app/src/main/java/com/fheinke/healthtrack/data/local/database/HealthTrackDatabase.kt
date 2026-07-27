package com.fheinke.healthtrack.data.local.database

import com.fheinke.healthtrack.data.local.entity.*

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        HealthEntryEntity::class,
        ActivityEntryEntity::class,
        BloodPressureEntryEntity::class,
        MoodEntryEntity::class,
        SleepEntryEntity::class,
        WeightEntryEntity::class
    ],
    version = 1
)
@TypeConverters(com.fheinke.healthtrack.data.local.converter.HealthTrackConverter::class)
abstract class HealthTrackDatabase : RoomDatabase() {
    abstract fun healthEntryDao(): com.fheinke.healthtrack.data.local.dao.HealthEntryDao
}
