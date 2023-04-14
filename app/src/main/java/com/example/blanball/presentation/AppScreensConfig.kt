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
import com.example.blanball.presentation.views.screens.ResetPasswordScreenStep1
import com.example.blanball.presentation.views.screens.ResetPasswordScreenStep2
import com.example.blanball.presentation.views.screens.ResetPasswordScreenStep3

@Composable
fun AppScreensConfig(navController: NavHostController, resetPassViewModel: ResetPasswordViewModel) {
    NavHost(navController = navController, startDestination = Destinations.RESET1.route)
    {
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
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            ResetPasswordScreenStep1(state = state,
                onStep2Clicked = {
                    resetPassViewModel.handleEvent(MainContract.Event.SendEmailResetRequestClicked)
                    navController.navigate(Destinations.RESET2.route)
                })
        }

        composable(Destinations.RESET2.route) {
            val state = resetPassViewModel
                .uiState
                .collectAsState()
                .value
            ResetPasswordScreenStep2(
                state = state,
                onStep3Clicked = {
                    resetPassViewModel.handleEvent(MainContract.Event.SendCodeClicked)
                    navController.navigate(Destinations.RESET3.route)
                }
            )
        }

        composable(Destinations.RESET3.route) {
            val state = resetPassViewModel
                .uiState
                .collectAsState()
                .value
            ResetPasswordScreenStep3(state = state,
                onFinishResetClicked = {
                    resetPassViewModel.handleEvent(MainContract.Event.CompleteResetClicked)
                }
            )
        }
    }
}

enum class  Destinations(val route: String) {
    RESET1("reset1"),
    RESET2("reset2"),
    RESET3("reset3"),
}