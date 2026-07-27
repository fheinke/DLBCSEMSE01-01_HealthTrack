package com.fheinke.healthtrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import com.fheinke.healthtrack.data.repository.HealthEntryRepository
import com.fheinke.healthtrack.domain.model.HealthEntry

class HealthEntryViewModel(private val repository: HealthEntryRepository) : ViewModel() {
    fun addHealthEntry(entry: HealthEntry) {
        viewModelScope.launch {
            repository.addHealthEntry(entry)
        }
    }

    val entries: StateFlow<List<HealthEntry>> = repository
        .getAllHealthEntries(userId = 1L)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}