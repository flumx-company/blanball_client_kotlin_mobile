package com.example.blanball.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.views.LoginScreen
import kotlin.math.log

@Composable
fun AppScreensConfig(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
) {
    NavHost(navController = navController, startDestination = Destinations.LOGIN.route) {
        composable(Destinations.LOGIN.route) {
            val state = loginViewModel
                .uiState
                .collectAsState()
                .value

            val context = LocalContext.current
            LaunchedEffect(key1 = true) {
                loginViewModel.sideEffect.collect {
                    when(it){
                        is MainContract.Effect.ShowToast -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            LoginScreen(
                state = state,
                onLoginClicked = {
                    loginViewModel.handleEvent(MainContract.Event.LoginClicked)
                }
            )
        }
    }
}


enum class Destinations(val route: String) {
    LOGIN("login"),
    SETTINGS("settings")
}
