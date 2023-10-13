package com.example.blanball.presentation

import Destinations
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.blanball.presentation.navigation.AppScreensConfig
import com.example.blanball.presentation.theme.MyAppTheme
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.views.screens.splash.SplashScreen
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.VerifyCodeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var rememberMeManager: RememberMeManager

    @Inject
    lateinit var tokenManager: TokenManager

    @Inject
    lateinit var userNameManager: UserNameManager

    @Inject
    lateinit var userAvatarUrlManager: UserAvatarUrlManager

    @Inject
    lateinit var userPhoneManager: UserPhoneManager

    @Inject
    lateinit var verifyCodeManager: VerifyCodeManager

    private val navigationDrawerViewModel: NavigationDrawerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        setContent {
            var isRememberMeFlagActive by rememberSaveable { mutableStateOf(false) }
            var isLaunchedEffectComplete by rememberSaveable {mutableStateOf(false)}
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()

            LaunchedEffect(key1 = Unit) {
                val userFullName: String? = userNameManager.getUserName().firstOrNull()
                val userAvatarUrl: String? = userAvatarUrlManager.getAvatarUrl().firstOrNull()
                userFullName?.let { fullName ->
                    val (firstName, lastName) = fullName.split(" ")
                    navigationDrawerViewModel.setState {
                        copy(
                            userFirstNameText = mutableStateOf(firstName),
                            userLastNameText = mutableStateOf(lastName),
                            userAvatar =  mutableStateOf(userAvatarUrl)
                        )
                    }
                }

                isRememberMeFlagActive = rememberMeManager.getRememberMeFlag().first() == true
                if (!isRememberMeFlagActive && (tokenManager.getAccessToken()
                        .first() != null) && (tokenManager.getAccessToken().first() != null)
                ) {
                    tokenManager.deleteAccessToken()
                    tokenManager.deleteRefreshToken()
                }
                isLaunchedEffectComplete = true
            }

            if (!isLaunchedEffectComplete) {
              SplashScreen()
            } else {
            val startDestinations = if (isRememberMeFlagActive) {
                Destinations.PUBLIC_PROFILE.route
            } else {
                Destinations.LOGIN.route
            }
                MyAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AppScreensConfig(
                            navController = navController,
                            resetPassViewModel = viewModel(),
                            registrationViewModel = viewModel(),
                            publicProfileViewModel = viewModel(),
                            loginViewModel = viewModel(),
                            onboardingProfileViewModel = viewModel(),
                            navigationDrawerViewModel = navigationDrawerViewModel,
                            startDestinations = startDestinations,
                            scaffoldState = scaffoldState,
                            coroutineScope = coroutineScope,
                            rememberMeManager = rememberMeManager,
                            tokenManager = tokenManager,
                            userNameManager = userNameManager ,
                            userAvatarUrlManager = userAvatarUrlManager,
                            userPhoneManager = userPhoneManager,
                            verifyCodeManager = verifyCodeManager,
                            foundAnErrorViewModel = viewModel(),
                            myProfileScreenViewModel = viewModel(),
                            eventCreationScreenViewModel = viewModel(),
                            futureEventsScreenViewModel = viewModel(),
                        )
                    }
                }
            }
        }
    }
}