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
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.views.ResetPasswordScreen

@Composable
fun AppScreensConfig(navController: NavHostController, resetPassViewModel: ResetPasswordViewModel) {
    NavHost(navController = navController, startDestination = Destinations.RESET1.route) {
        composable(Destinations.RESET1.route) {
            val state = resetPassViewModel
                .uiState
                .collectAsState()
                .value

            val context = LocalContext.current
            LaunchedEffect(key1 = true) {
                resetPassViewModel.sideEffect.collect {
                    when (it) {
                        is MainContract.Effect.ShowToast -> {
                            Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            ResetPasswordScreen(state = state,
                onStep2Clicked = {
                    resetPassViewModel.handleEvent(MainContract.Event.SendCodeClicked)
                })
        }
    }
}

enum class  Destinations(val route: String) {
    RESET1("reset1"),
    RESET2("reset2"),
}