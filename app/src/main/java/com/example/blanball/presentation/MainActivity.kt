package com.example.blanball.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.blanball.presentation.theme.MyAppTheme
import com.example.blanball.presentation.views.screens.splash.SplashScreen
import com.example.blanball.utils.navigateToLogin
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var rememberMeManager: RememberMeManager

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        setContent {
            var isRememberMeFlagActive by rememberSaveable { mutableStateOf(false) }
            var isLaunchedEffectComplete by rememberSaveable {mutableStateOf(false)}

            val navController = rememberNavController()
            LaunchedEffect(key1 = Unit) {
                isRememberMeFlagActive = rememberMeManager.getRememberMeFlag().first() == true
                if (!isRememberMeFlagActive && (tokenManager.getAccessToken()
                        .first() != null) && (tokenManager.getAccessToken().first() != null)
                ) {
                    tokenManager.deleteAccessToken()
                    tokenManager.deleteRefreshToken()
                }
                delay(1000)
                isLaunchedEffectComplete = true
            }

            LaunchedEffect(key1 = navigateToLogin.value) {
                if (navigateToLogin.value) {
                    rememberMeManager.deleteRememberMeFlag()
                    navController.navigate(Destinations.LOGIN.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                    navigateToLogin.value = false
                }
            }

            if (!isLaunchedEffectComplete) {
              SplashScreen()
            } else {
            val startDestinations = if (isRememberMeFlagActive) {
                BottomNavItem.Home.screen_route
            } else {
                Destinations.LOGIN.route
            }
                MyAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AppScreensConfig(
                            navController = navController,
                            startDestinations = startDestinations,
                            resetPassViewModel = viewModel(),
                            registrationViewModel = viewModel(),
                            publicProfileViewModel = viewModel(),
                            loginViewModel = viewModel(),
                            onboardingProfileViewModel = viewModel(),
                            usersRatingViewModel = viewModel()
                        )
                    }
                }
            }
        }
    }
}