package com.fheinke.healthtrack.domain.model

enum class ActivityType {
    WALKING,
    HIKING,
    RUNNING,
    CYCLING,
    SWIMMING
}

enum class DataSource {
    MANUAL,
    SENSOR,
    IMPORTED
}

enum class EntryType {
    ACTIVITY,
    BLOOD_PRESSURE,
    MOOD,
    WEIGHT,
    SLEEP
}

enum class GenderType {
    FEMALE,
    MALE,
    OTHER
}

enum class SyncStatus {
    LOCAL_ONLY,
    PENDING_SYNC,
    SYNCED,
    SYNC_FAILED
}

