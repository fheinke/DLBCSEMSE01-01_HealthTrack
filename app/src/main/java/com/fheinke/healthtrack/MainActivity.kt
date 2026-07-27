package com.fheinke.healthtrack

import android.os.Bundle
import androidx.room.Room
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fheinke.healthtrack.data.local.database.HealthTrackDatabase
import com.fheinke.healthtrack.data.repository.HealthEntryRepository
import com.fheinke.healthtrack.ui.screen.HealthEntryScreen
import com.fheinke.healthtrack.ui.theme.HealthTrackTheme
import com.fheinke.healthtrack.ui.viewmodel.HealthEntryViewModel

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            HealthTrackDatabase::class.java,
            "healthtrack.db"
        ).build()
    }

    private val repository by lazy {
        HealthEntryRepository(database.healthEntryDao())
    }

    private val viewModel: HealthEntryViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass : Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return HealthEntryViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthTrackTheme {
                HealthEntryScreen(viewModel)
            }
        }
    }
}
