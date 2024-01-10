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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.blanball.presentation.data.TechWorksScreenMainContract
import com.example.blanball.presentation.navigation.AppScreensConfig
import com.example.blanball.presentation.navigation.BottomNavItem
import com.example.blanball.presentation.theme.MyAppTheme
import com.example.blanball.presentation.viewmodels.EventCreationOrEditScreensViewModel
import com.example.blanball.presentation.viewmodels.EventScreenViewModel
import com.example.blanball.presentation.viewmodels.FutureEventsScreenViewModel
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.viewmodels.TechWorksScreenViewModel
import com.example.blanball.presentation.views.screens.splash.SplashScreen
import com.example.blanball.presentation.views.screens.technicalworks.TechnicalWorksScreen
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.useremailmanager.UserEmailManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
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
    lateinit var resetPassVerifyCodeManager: ResetPassVerifyCodeManager

    @Inject
    lateinit var userEmailManager: UserEmailManager


    private val navigationDrawerViewModel: NavigationDrawerViewModel by viewModels()
    private val futureEventsScreenViewModel: FutureEventsScreenViewModel by viewModels()
    private val techWorksScreenViewModel: TechWorksScreenViewModel by viewModels()
    private val eventScreenViewModel: EventScreenViewModel by viewModels()
    private val eventCreationScreenViewModel: EventCreationOrEditScreensViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        setContent {
            var isRememberMeFlagActive by rememberSaveable { mutableStateOf(false) }
            var isLaunchedEffectComplete by rememberSaveable { mutableStateOf(false) }
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()
            val navItems = remember {
                listOf(
                    BottomNavItem.Home,
                    BottomNavItem.FutureEvents,
                    BottomNavItem.CreateNewEvent,
                    BottomNavItem.Rating,
                    BottomNavItem.Chat,
                )
            }

            LaunchedEffect(key1 = Unit) {
                navigationDrawerViewModel.getMyProfile() //TODO() Make it encapsulated - without calling the method directly
                val userFullName: String? = userNameManager.getUserName().firstOrNull()
                val userAvatarUrl: String? = userAvatarUrlManager.getAvatarUrl().firstOrNull()
                userFullName?.let { fullName ->
                    val (firstName, lastName) = fullName.split(" ")
                    techWorksScreenViewModel.handleScreenState(TechWorksScreenMainContract.ScreenViewState.Loading)
                    navigationDrawerViewModel.setState {
                        copy(
                            userFirstNameText = mutableStateOf(firstName),
                            userLastNameText = mutableStateOf(lastName),
                            userAvatar = mutableStateOf(userAvatarUrl)
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

            val isTechWorksStatus = techWorksScreenViewModel.currentState.isTechWorksAvailable.value

            when {
                !isLaunchedEffectComplete
                -> {
                    SplashScreen()
                }

                isTechWorksStatus -> {
                    TechnicalWorksScreen()
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
                                        eventCreationScreenViewModel = viewModel(),
                                        myEventsViewModel = viewModel(),
                                        futureEventsScreenViewModel = futureEventsScreenViewModel,
                                        eventScreenViewModel = viewModel(),
                                        emailVerificationViewModel = viewModel(),
                                        userEmailManager = userEmailManager,
                                        eventCreationOrEditViewModel = viewModel(),
                                    )
                        }
                    }
                }
            }
        }
    }
}