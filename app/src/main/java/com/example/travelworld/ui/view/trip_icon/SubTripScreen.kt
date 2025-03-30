import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.ui.viewmodel.SubTripViewModel

@Composable
fun SubTripContent(
    navController: NavController,
    tripId: Int,
    viewModel: SubTripViewModel = hiltViewModel()
) {
    val subTrips = viewModel.subTrips

    // Estados para el diálogo de edición/creación
    var showDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var currentSubTripId by remember { mutableStateOf(0) }
    var subTripTitle by remember { mutableStateOf("") }
    var subTripDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Botón de regreso
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
            Text("Volver")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(subTrips) { subTrip ->
                SubTripItem(
                    subTrip = subTrip,
                    onEdit = {
                        isEditing = true
                        currentSubTripId = subTrip.id
                        subTripTitle = subTrip.title
                        subTripDescription = subTrip.description
                        showDialog = true
                    },
                    onDelete = { viewModel.deleteSubTrip(subTrip.id) }
                )
            }
        }

        // Botón para añadir nueva subtarea
        Button(
            onClick = {
                isEditing = false
                subTripTitle = ""
                subTripDescription = ""
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir Subtarea")
            Text("Añadir Subtarea")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (isEditing) "Editar Subtarea" else "Nueva Subtarea") },
            text = {
                Column {
                    OutlinedTextField(
                        value = subTripTitle,
                        onValueChange = { subTripTitle = it },
                        label = { Text("Título") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = subTripDescription,
                        onValueChange = { subTripDescription = it },
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
                        if (isEditing) {
                            viewModel.updateSubTrip(
                                SubTrip(
                                    id = currentSubTripId,
                                    parentTripId = tripId,
                                    title = subTripTitle,
                                    description = subTripDescription
                                )
                            )
                        } else {
                            viewModel.addSubTrip(
                                SubTrip(
                                    parentTripId = tripId,
                                    title = subTripTitle,
                                    description = subTripDescription
                                )
                            )
                        }
                        showDialog = false
                    }
                ) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Composable
fun SubTripItem(
    subTrip: SubTrip,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = subTrip.title,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onEdit) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Editar Subtarea"
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Borrar Subtarea"
            )
        }
    }
}
