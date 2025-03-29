package com.example.travelworld

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelworld.ui.view.settings_option.AboutScreen
import com.example.travelworld.ui.view.settings_option.SettingsScreen
import com.example.travelworld.ui.view.settings_option.TermsConditionsScreen

import com.example.travelworld.ui.view.TravelApp
import com.example.travelworld.ui.view.LoginScreen
import com.example.travelworld.ui.view.settings_option.VersionScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { TravelApp(navController) }
        composable("about") { AboutScreen(navController) }
        composable("terms") { TermsConditionsScreen(navController) }
        composable("version") { VersionScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
