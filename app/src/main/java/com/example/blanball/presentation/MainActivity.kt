package com.example.blanball.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.blanball.presentation.theme.MyAppTheme
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import dagger.hilt.android.AndroidEntryPoint
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

        setContent {
            var rememberMeFlag by remember { mutableStateOf(false) }
            var launchedEffectComplete by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = Unit) {
                rememberMeFlag = rememberMeManager.getRememberMeFlag().first() == true
              if (!rememberMeFlag && (tokenManager.getAccessToken().first() != null) && (tokenManager.getAccessToken().first() != null )) {
                  tokenManager.deleteAccessToken()
                  tokenManager.deleteRefreshToken()
              }
                launchedEffectComplete = true
            }

            val startDestinations = if (rememberMeFlag) {
                Destinations.PUBLIC_PROFILE.route
            } else {
                Destinations.LOGIN.route
            }

            if (launchedEffectComplete) {
                MyAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background,
                    ) {
                        AppScreensConfig(
                            navController = rememberNavController(),
                            resetPassViewModel = viewModel(),
                            registrationViewModel = viewModel(),
                            publicProfileViewModel = viewModel(),
                            loginViewModel = viewModel(),
                            onboardingProfileViewModel = viewModel(),
                            startDestinations = startDestinations,
                        )
                    }
                }
            }
        }
    }
}