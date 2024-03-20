package com.example.blanball.presentation

import Destinations
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.blanball.R
import com.example.blanball.presentation.data.NavigationDrawerMainContract
import com.example.blanball.presentation.data.TechWorksScreenMainContract
import com.example.blanball.presentation.navigation.AppScreensConfig
import com.example.blanball.presentation.theme.MyAppTheme
import com.example.blanball.presentation.viewmodels.EmailVerificationViewModel
import com.example.blanball.presentation.viewmodels.FutureEventsScreenViewModel
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.viewmodels.SelectLocationScreenViewModel
import com.example.blanball.presentation.viewmodels.TechWorksScreenViewModel
import com.example.blanball.presentation.views.screens.splash.SplashScreen
import com.example.blanball.presentation.views.screens.technicalworks.TechnicalWorksScreen
import com.example.blanball.utils.SessionManager
import com.example.data.datastore.emailverificationmanager.EmailVerificationManager
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.useremailmanager.UserEmailManager
import com.example.data.datastore.useridmanager.UserIdManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
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
    lateinit var resetPassVerifyCodeManager: ResetPassVerifyCodeManager

    @Inject
    lateinit var userEmailManager: UserEmailManager

    @Inject
    lateinit var emailVerificationManager: EmailVerificationManager

    @Inject
    lateinit var userIdManager: UserIdManager

    private val navigationDrawerViewModel: NavigationDrawerViewModel by viewModels()
    private val futureEventsScreenViewModel: FutureEventsScreenViewModel by viewModels()
    private val techWorksScreenViewModel: TechWorksScreenViewModel by viewModels()
    private val emailVerificationViewModel: EmailVerificationViewModel by viewModels()
    private val selectLocationScreenViewModel: SelectLocationScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            SessionManager(
                rememberMeManager = rememberMeManager,
                tokenManager = tokenManager,
                userNameManager = userNameManager,
                userAvatarUrlManager = userAvatarUrlManager,
                userPhoneManager = userPhoneManager,
                resetPassVerifyCodeManager = resetPassVerifyCodeManager,
                userEmailManager = userEmailManager,
                emailVerificationManager = emailVerificationManager,
                userIdManager = userIdManager,
            )
                .cleanDataStoreWithChecking()
                .await()
        }

        actionBar?.hide()
        setContent {
            var isConnectException by remember { mutableStateOf(false) }
            var isRememberMeFlagActive by rememberSaveable { mutableStateOf(false) }
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()

            Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
                if (throwable is java.net.ConnectException) {
                    isConnectException = true
                }
            }


            if (navigationDrawerViewModel.currentState.isSplashScreenActivated.value) {
                LaunchedEffect(key1 = Unit) {
                    navigationDrawerViewModel.handleEvent(NavigationDrawerMainContract.Event.GetLaunchData)
                    val isEmailVerificationVMCurrentState = emailVerificationViewModel.currentState
                    val userFullName = userNameManager.getUserName().firstOrNull()
                    val userAvatarUrl = userAvatarUrlManager.getAvatarUrl().firstOrNull()
                    val userEmail = userEmailManager.getUserEmail().firstOrNull()
                    coroutineScope.launch(Dispatchers.IO) {
                        emailVerificationManager.getIsEmailVerified()
                            .collect { isEmailVerified ->
                                isEmailVerificationVMCurrentState.isEmailVerified.value =
                                    isEmailVerified == true
                            }
                    }
                    isEmailVerificationVMCurrentState.userEmailText.value = userEmail ?: ""
                    userFullName?.let { fullName ->
                        val names = fullName.split(" ")
                        if (names.size >= 2) {
                            val (firstName, lastName) = names
                            techWorksScreenViewModel.handleScreenState(
                                TechWorksScreenMainContract.ScreenViewState.Loading
                            )
                            navigationDrawerViewModel.setState {
                                copy(
                                    userFirstNameText = mutableStateOf(firstName),
                                    userLastNameText = mutableStateOf(lastName),
                                    userAvatar = mutableStateOf(userAvatarUrl)
                                )
                            }
                        }
                    }

                    isRememberMeFlagActive = rememberMeManager.getRememberMeFlag().first() == true
                    navigationDrawerViewModel.currentState.isSplashScreenActivated.value = false
                }
            }

            val isTechWorksStatus = techWorksScreenViewModel.currentState.isTechWorksAvailable.value

            when {
                isConnectException -> {
                    TechnicalWorksScreen(
                        messageTextId = R.string.connection_error,
                        secondaryTextId = R.string.check_your_connection,
                    )
                }

                navigationDrawerViewModel.currentState.isSplashScreenActivated.value
                -> {
                    SplashScreen()
                }

                isTechWorksStatus -> {
                    TechnicalWorksScreen(
                        messageTextId = R.string.technical_works,
                        secondaryTextId = R.string.our_team_works,
                    )
                }

                else -> {
                    val startDestinations = if (isRememberMeFlagActive) {
                        Destinations.HOME.route
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
                                navigationDrawerViewModel = navigationDrawerViewModel,
                                scaffoldState = scaffoldState,
                                coroutineScope = coroutineScope,
                                rememberMeManager = rememberMeManager,
                                tokenManager = tokenManager,
                                userNameManager = userNameManager,
                                userAvatarUrlManager = userAvatarUrlManager,
                                userPhoneManager = userPhoneManager,
                                resetPassVerifyCodeManager = resetPassVerifyCodeManager,
                                usersRatingViewModel = viewModel(),
                                foundAnErrorViewModel = viewModel(),
                                myProfileScreenViewModel = viewModel(),
                                myEventsViewModel = viewModel(),
                                futureEventsScreenViewModel = futureEventsScreenViewModel,
                                eventScreenViewModel = viewModel(),
                                emailVerificationViewModel = viewModel(),
                                userEmailManager = userEmailManager,
                                emailVerificationManager = emailVerificationManager,
                                selectLocationScreenViewModel = selectLocationScreenViewModel,
                                userIdManager = userIdManager,
                            )
                        }
                    }
                }
            }
        }
    }
}