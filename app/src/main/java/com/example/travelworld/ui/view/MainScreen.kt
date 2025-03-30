package com.example.travelworld.ui.view

import HomeApp
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelworld.ui.view.trip_icon.TripApp
import com.example.travelworld.ui.view.userpref_icon.UserPreferencesApp

// Enum para representar las pantallas principales
enum class TravelMode {
    HOME, TRIP, ITINERARY, USER_PREFERENCE
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelApp(navController: NavController) {
    // Estado que guarda la pantalla actual
    var selectedScreen by remember { mutableStateOf(TravelMode.HOME) }
    var showSettingsMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Travel App") },
                actions = {
                    Box {
                        IconButton(onClick = { showSettingsMenu = !showSettingsMenu }) {
                            Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                        }
                        DropdownMenu(
                            expanded = showSettingsMenu,
                            onDismissRequest = { showSettingsMenu = false }
                        ) {
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "About Icon") },
                                text = { Text("About") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Build, contentDescription = "Version Icon") },
                                text = { Text("Version") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("version")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Profile Icon") },
                                text = { Text("Profile") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("profile")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = "Settings Icon") },
                                text = { Text("Settings") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("settings")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = {Icon (Icons.Filled.Policy, contentDescription = "Terms & Conditions") },
                                text = { Text("Term&Conditions") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("terms")
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedScreen == TravelMode.HOME,
                    onClick = { selectedScreen = TravelMode.HOME },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Flight, contentDescription = "Trip") },
                    selected = selectedScreen == TravelMode.TRIP,
                    onClick = { selectedScreen = TravelMode.TRIP },
                    label = { Text("Trip") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Itinerary") },
                    selected = selectedScreen == TravelMode.ITINERARY,
                    onClick = { selectedScreen = TravelMode.ITINERARY },
                    label = { Text("Itinerary") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "User Preference") },
                    selected = selectedScreen == TravelMode.USER_PREFERENCE,
                    onClick = { selectedScreen = TravelMode.USER_PREFERENCE },
                    label = { Text("Preferences") }
                )
            }
        }
    ) { innerPadding ->
        // Contenido que se actualiza según la pantalla seleccionada
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedScreen) {
                TravelMode.HOME -> HomeScreen()
                TravelMode.TRIP -> TripScreen()
                TravelMode.ITINERARY -> ItineraryScreen()
                TravelMode.USER_PREFERENCE -> UserPreferenceScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)
        HomeApp()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Trip Screen", style = MaterialTheme.typography.headlineMedium)
        //integracion del nueva pantalla trip
        TripApp(navController = rememberNavController())
    }
}

@Composable
fun ItineraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Itinerary Screen", style = MaterialTheme.typography.headlineMedium)

    }
}

@Composable
fun UserPreferenceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User Preference Screen", style = MaterialTheme.typography.headlineMedium)
        // Aquí puedes agregar los componentes y lógica de la pantalla de preferencias de usuario
        UserPreferencesApp()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainAppPreview() {
    TravelApp(navController = rememberNavController())
}