package com.fheinke.healthtrack.data.local.converter

import androidx.room.TypeConverter
import com.fheinke.healthtrack.domain.model.ActivityType
import com.fheinke.healthtrack.domain.model.DataSource
import com.fheinke.healthtrack.domain.model.EntryType
import com.fheinke.healthtrack.domain.model.GenderType
import com.fheinke.healthtrack.domain.model.SyncStatus

class HealthTrackConverter {
    @TypeConverter
    fun fromActivityType(value: ActivityType): String = value.name
   @TypeConverter
    fun toActivityType(value: String): ActivityType = ActivityType.valueOf(value)

    @TypeConverter
    fun fromDataSource(value: DataSource): String = value.name
    @TypeConverter
    fun toDataSource(value: String): DataSource = DataSource.valueOf(value)

    @TypeConverter
    fun fromEntryType(value: EntryType): String = value.name
    @TypeConverter
    fun toEntryType(value: String): EntryType = EntryType.valueOf(value)

    @TypeConverter
    fun fromGenderType(value: GenderType): String = value.name
    @TypeConverter
    fun toGenderType(value: String): GenderType = GenderType.valueOf(value)

    @TypeConverter
    fun fromSyncStatus(value: SyncStatus): String = value.name
    @TypeConverter
    fun toSyncStatus(value: String): SyncStatus = SyncStatus.valueOf(value)
}