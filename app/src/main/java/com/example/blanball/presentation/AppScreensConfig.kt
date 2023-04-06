package com.example.blanball.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.viewmodels.ResetPasswordStep1ViewModel

@Composable
fun AppScreensConfig(navController: NavHostController, resetPassViewModel: ResetPasswordStep1ViewModel) {
    NavHost(navController = navController, startDestination = Destinations.RESET1.route) {
        composable(Destinations.RESET1.route) {
            val state = resetPassViewModel
                .uiState
                .
        }
    }
}

enum class  Destinations(val route: String) {
    RESET1("reset1"),
    RESET2("reset2"),
}