package com.example.travelworld.ui.view.trip_icon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.viewmodel.TripViewModel

@Composable
fun TripApp(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {
    // Recolecta el estado del flujo
    val trips by viewModel.trips.collectAsState()

    var showTripDialog by remember { mutableStateOf(false) }
    var isEditingTrip by remember { mutableStateOf(false) }
    var currentTripId by remember { mutableStateOf(0) }
    var tripTitle by remember { mutableStateOf("") }
    var tripDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Lista de tareas
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(trips) { trip ->  // Ahora trips es List<Trip>
                TripItem(
                    trip = trip,
                    onEdit = {
                        isEditingTrip = true
                        currentTripId = trip.id
                        tripTitle = trip.title
                        tripDescription = trip.description
                        showTripDialog = true
                    },
                    onOpen = {
                        navController.navigate("subtrips/${trip.id}")
                    }
                )
            }
        }

        // Modal para crear o editar tarea
        if (showTripDialog) {
            AlertDialog(
                onDismissRequest = { showTripDialog = false },
                title = { Text(text = if (isEditingTrip) "Editar Tarea" else "Nueva Tarea") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = tripTitle,
                            onValueChange = { tripTitle = it },
                            label = { Text("Título") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = tripDescription,
                            onValueChange = { tripDescription = it },
                            label = { Text("Descripción") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (isEditingTrip) {
                                viewModel.updateTrip(
                                    Trip(
                                        id = currentTripId,
                                        title = tripTitle,
                                        description = tripDescription
                                    )
                                )
                            } else {
                                viewModel.addTrip(
                                    Trip(
                                        title = tripTitle,
                                        description = tripDescription
                                    )
                                )
                            }
                            showTripDialog = false
                        }
                    ) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showTripDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
@Composable
fun TripItem(
    trip: Trip,
    onEdit: () -> Unit,
    onOpen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        // Muestra el título de la tarea
        Text(
            text = trip.title,
            modifier = Modifier.weight(1f)
        )
        // Icono para editar (abre modal)
        IconButton(onClick = onEdit) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Editar Tarea"
            )
        }
        // Icono para abrir el listado de subtareas
        IconButton(onClick = onOpen) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Abrir Subtareas"
            )
        }
    }
}