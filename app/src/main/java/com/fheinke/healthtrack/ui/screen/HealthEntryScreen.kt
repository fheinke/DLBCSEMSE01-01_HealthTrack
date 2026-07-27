package com.fheinke.healthtrack.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.fheinke.healthtrack.domain.model.DataSource
import com.fheinke.healthtrack.domain.model.EntryType
import com.fheinke.healthtrack.domain.model.HealthEntry
import com.fheinke.healthtrack.ui.viewmodel.HealthEntryViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HealthEntryScreen(viewModel: HealthEntryViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Menu) }

    when (currentScreen) {
        Screen.Menu -> MenuScreen(
            onAddEntry = { currentScreen = Screen.AddEntry },
            onListEntries = { currentScreen = Screen.ListEntries }
        )
        Screen.AddEntry -> AddEntryScreen(
            viewModel = viewModel,
            onBack = { currentScreen = Screen.Menu }
        )
        Screen.ListEntries -> ListEntriesScreen(
            viewModel = viewModel,
            onBack = { currentScreen = Screen.Menu }
        )
    }
}

sealed class Screen {
    object Menu : Screen()
    object AddEntry : Screen()
    object ListEntries : Screen()
}

@Composable
fun MenuScreen(onAddEntry: () -> Unit, onListEntries: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("HealthTrack", style = MaterialTheme.typography.headlineLarge, color = Color.White)
        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = onAddEntry, modifier = Modifier.fillMaxWidth()) {
            Text("Create New Entry")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onListEntries, modifier = Modifier.fillMaxWidth()) {
            Text("View All Entries")
        }
    }
}

@Composable
fun AddEntryScreen(viewModel: HealthEntryViewModel, onBack: () -> Unit) {
    var note by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(EntryType.MOOD) }
    var saved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("New Entry", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))

        // Type selection
        Text("Type:", style = MaterialTheme.typography.labelLarge, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        EntryType.entries.forEach { type ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedType == type,
                    onClick = { selectedType = type }
                )
                Text(type.name, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note (optional)", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.addHealthEntry(
                    HealthEntry(
                        userId = 1L,
                        entryType = selectedType,
                        source = DataSource.MANUAL,
                        note = note.ifBlank { null }
                    )
                )
                saved = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        if (saved) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Entry saved!", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onBack) { Text("Back") }
    }
}

@Composable
fun ListEntriesScreen(viewModel: HealthEntryViewModel, onBack: () -> Unit) {
    val entries by viewModel.entries.collectAsState()
    val dateFormat = remember { SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY) }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("All Entries", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (entries.isEmpty()) {
            Text("No entries available.", color = MaterialTheme.colorScheme.outline)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(entries) { entry ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(entry.entryType.name, style = MaterialTheme.typography.titleMedium)
                            Text(dateFormat.format(Date(entry.createdAt)),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline)
                            entry.note?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(it)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onBack) { Text("Back") }
    }
}