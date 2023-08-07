package com.example.blanball.presentation.navigation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.viewmodels.OnboardingProfileViewModel
import com.example.blanball.presentation.viewmodels.PublicProfileViewModel
import com.example.blanball.presentation.viewmodels.RegistrationViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.views.components.bottomnavbars.BottomNavBar
import com.example.blanball.presentation.views.components.drawers.NavigationDrawer
import com.example.blanball.presentation.views.components.topbars.TopBar
import com.example.blanball.presentation.views.screens.chats.ChatsScreen
import com.example.blanball.presentation.views.screens.createnewevent.CreateNewEventScreen
import com.example.blanball.presentation.views.screens.friends.FriendsScreen
import com.example.blanball.presentation.views.screens.futureevents.FutureEventsScreen
import com.example.blanball.presentation.views.screens.home.HomeScreen
import com.example.blanball.presentation.views.screens.login.LoginScreen
import com.example.blanball.presentation.views.screens.myprofile.MyProfileScreen
import com.example.blanball.presentation.views.screens.notifications.NotificationsScreen
import com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile.FillingOutTheUserProfileScreenStep1
import com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile.FillingOutTheUserProfileScreenStep2
import com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile.FillingOutTheUserProfileScreenStep3
import com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile.FillingOutTheUserProfileScreenStep4
import com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile.FillingOutTheUserProfileStartScreen
import com.example.blanball.presentation.views.screens.onboarding.usertraining.UserTrainingStep1
import com.example.blanball.presentation.views.screens.onboarding.usertraining.UserTrainingStep2
import com.example.blanball.presentation.views.screens.onboarding.usertraining.UserTrainingStep3
import com.example.blanball.presentation.views.screens.onboarding.usertraining.UserTrainingStep4
import com.example.blanball.presentation.views.screens.plannedevents.PlannedEventsScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllPlannedEventsScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllReviewsScreen
import com.example.blanball.presentation.views.screens.publicprofile.PublicProfileScreen
import com.example.blanball.presentation.views.screens.rating.RatingScreen
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep1
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep2
import com.example.blanball.presentation.views.screens.resset.NewPasswordSuccessfullySavedScreen
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep1
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep3
import com.example.blanball.presentation.views.screens.settings.SettingsScreen
import com.example.blanball.presentation.views.screens.versions.VersionsScreen
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.VerifyCodeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppScreensConfig(
    navController: NavHostController,
    resetPassViewModel: ResetPasswordViewModel,
    registrationViewModel: RegistrationViewModel,
    publicProfileViewModel: PublicProfileViewModel,
    loginViewModel: LoginViewModel,
    onboardingProfileViewModel: OnboardingProfileViewModel,
    navigationDrawerViewModel: NavigationDrawerViewModel,
    startDestinations: String,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    rememberMeManager: RememberMeManager,
    tokenManager: TokenManager,
    userNameManager:  UserNameManager,
    userAvatarUrlManager: UserAvatarUrlManager,
    userPhoneManager: UserPhoneManager,
    verifyCodeManager: VerifyCodeManager,
) {
    val openDrawer: () -> Unit = {
        coroutineScope.launch {
            scaffoldState.drawerState.open()
        }
    }
    val closeDrawer: () -> Unit = {
        coroutineScope.launch {
            delay(200)
            scaffoldState.drawerState.close()
        }
    }
    val navDrawerContent: @Composable ColumnScope.() -> Unit = {
        val navigationDrawerState = navigationDrawerViewModel.uiState.collectAsState().value
        NavigationDrawer(
            state = navigationDrawerState,
            onFriendsScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.FRIENDS.route)
            },
            onPlannedEventsScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.PLANNED_EVENTS.route)
            },
            onNotificationsScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.NOTIFICATIONS.route)
            },
            onSettingsScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.SETTINGS.route)
            },
            onMyProfileScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.MY_PROFILE.route)
            },
            onVersionsScreenClicked = {
                closeDrawer()
                navController.navigate(Destinations.VERSIONS.route)
            },
            onLogOutClicked = {
                closeDrawer()
                navController.navigate(Destinations.LOGIN.route)
                {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
                coroutineScope.launch {
                    rememberMeManager.deleteRememberMeFlag()
                    tokenManager.deleteRefreshToken()
                    tokenManager.deleteAccessToken()
                    userAvatarUrlManager.deleteAvatarUrl()
                    userNameManager.deleteUserName()
                    userPhoneManager.deleteUserPhone()
                    verifyCodeManager.deleteVerifyCode()
                }
            },
        )
    }

    NavHost(
        navController = navController,
        startDestination = startDestinations
    )
    {
        composable(Destinations.LOGIN.route) {
            val state = loginViewModel.uiState.collectAsState().value
            val currentState = loginViewModel.currentState

            LoginScreen(
                state = state,
                onLoginClicked = {
                    loginViewModel.handleEvent(StartScreensMainContract.Event.LoginClicked)
                },
                dontRememberButtonClicked = { navController.navigate(Destinations.RESET1.route) },
                registrationButtonClicked = { navController.navigate(Destinations.REGISTRATION1.route) })

            LaunchedEffect(currentState.isSuccessLoginRequest.value) {
                if (currentState.isSuccessLoginRequest.value) {
                    currentState.isSuccessLoginRequest.value = false
                    navigationDrawerViewModel.getMyProfile()
                    navController.navigate(Destinations.PUBLIC_PROFILE.route)
                }
            }
        }

        composable(Destinations.RESET1.route) {
            val state = resetPassViewModel.uiState.collectAsState().value
            val currentState = resetPassViewModel.currentState

            ResetPasswordScreenStep1(
                state = state,
                onStep2Clicked = {
                    resetPassViewModel.handleEvent(StartScreensMainContract.Event.SendEmailResetRequestClicked)
                },
                onCancelClicked = {
                    navController.navigate(Destinations.LOGIN.route)
                     resetPassViewModel.setState  {
                         copy(
                             resetEmailText = mutableStateOf(""),
                             codeText = List(5){ mutableStateOf("") },
                             newPassText = mutableStateOf(""),
                             repeatNewPassText = mutableStateOf(""),
                         )
                     }
                }
            )

            LaunchedEffect(currentState.isSuccessResetRequest.value) {
                if (currentState.isSuccessResetRequest.value) {
                    currentState.isSuccessResetRequest.value = false
                    navController.navigate(Destinations.RESET2.route)
                }
            }
        }

        composable(Destinations.RESET2.route) {
            val state = resetPassViewModel.uiState.collectAsState().value
            val currentState = resetPassViewModel.currentState

            ResetPasswordScreenStep2(
                state = state,
                onStep3Clicked = {
                    resetPassViewModel.handleEvent(StartScreensMainContract.Event.SendCodeClicked)
                },
                resendCodeToEmailClicked = { resetPassViewModel.handleEvent(StartScreensMainContract.Event.SendEmailResetRequestClicked) },
                onCancelClicked = {
                    navController.navigate(Destinations.LOGIN.route)
                    resetPassViewModel.setState  {
                        copy(
                            resetEmailText = mutableStateOf(""),
                            codeText = List(5){ mutableStateOf("") },
                            newPassText = mutableStateOf(""),
                            repeatNewPassText = mutableStateOf(""),
                        )
                    }
                })

            LaunchedEffect(key1 = currentState.isSuccessSendCodeState.value) {
                if (currentState.isSuccessSendCodeState.value) {
                    currentState.isSuccessSendCodeState.value = false
                    navController.navigate(Destinations.RESET3.route)
                }
            }
        }

        composable(Destinations.RESET3.route) {
            val state = resetPassViewModel.uiState.collectAsState().value
            val currentState = resetPassViewModel.currentState

            ResetPasswordScreenStep3(state = state,
                onFinishResetClicked = {
                    resetPassViewModel.handleEvent(StartScreensMainContract.Event.CompleteResetClicked)
                },
                onCancelClicked = {
                    navController.navigate(Destinations.LOGIN.route)
                    resetPassViewModel.setState  {
                        copy(
                            resetEmailText = mutableStateOf(""),
                            codeText = List(5){ mutableStateOf("") },
                            newPassText = mutableStateOf(""),
                            repeatNewPassText = mutableStateOf(""),
                        )
                    }
                })

            LaunchedEffect(key1 = currentState.isSuccessCompleteResetState.value) {
                if (currentState.isSuccessCompleteResetState.value) {
                    currentState.isSuccessCompleteResetState.value = false
                    navController.navigate(Destinations.RESET_COMPLETE.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(Destinations.RESET_COMPLETE.route){

            NewPasswordSuccessfullySavedScreen(
                authToSystemClicked = {
                    navController.navigate(Destinations.LOGIN.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Destinations.REGISTRATION1.route) {
            val state = registrationViewModel.uiState.collectAsState().value
            RegistrationScreenStep1(
                state = state,
                onRegistrationStep2Clicked = { navController.navigate(Destinations.REGISTRATION2.route) },

                onCancelClicked = {
                    navController.navigate(Destinations.LOGIN.route)
                registrationViewModel.setState {
                    copy(
                        firstNameText = mutableStateOf(""),
                        lastNameText = mutableStateOf(""),
                        phoneNumberText = mutableStateOf(""),
                        genderIsFemale = mutableStateOf(false),
                        genderIsMale = mutableStateOf(false),
                        registrationEmailText = mutableStateOf(""),
                        registrationPassText = mutableStateOf(""),
                        registrationPassTextRemember = mutableStateOf(""),
                        lostInSystemSwitchButton = mutableStateOf(false),
                        privacyPolicyCheckbox = mutableStateOf(false),
                    )
                }
                })
        }

        composable(Destinations.REGISTRATION2.route) {
            val state = registrationViewModel.uiState.collectAsState().value
            val currentState = registrationViewModel.currentState

            RegistrationScreenStep2(
                state = state,
                onRegistrationClicked = {
                    registrationViewModel.handleEvent(StartScreensMainContract.Event.RegistrationClicked)
                },
                onCancelClicked = {
                    navController.navigate(Destinations.LOGIN.route)
                    registrationViewModel.setState {
                        copy(
                            firstNameText = mutableStateOf(""),
                            lastNameText = mutableStateOf(""),
                            phoneNumberText = mutableStateOf(""),
                            genderIsFemale = mutableStateOf(false),
                            genderIsMale = mutableStateOf(false),
                            registrationEmailText = mutableStateOf(""),
                            registrationPassText = mutableStateOf(""),
                            registrationPassTextRemember = mutableStateOf(""),
                            lostInSystemSwitchButton = mutableStateOf(false),
                            privacyPolicyCheckbox = mutableStateOf(false),
                        )
                    }
                })

            LaunchedEffect(key1 = currentState.isSuccessRegistrationNewPass.value) {
                if (currentState.isSuccessRegistrationNewPass.value) {
                    currentState.isSuccessRegistrationNewPass.value = false
                    navController.navigate(Destinations.USER_TRAINING_1.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(Destinations.PUBLIC_PROFILE.route) {
            val context = LocalContext.current
            val state = publicProfileViewModel.uiState.collectAsState().value
            val currentState = publicProfileViewModel.currentState

            LaunchedEffect(key1 = Unit) {
                publicProfileViewModel.loadUserProfileData()
            }
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    PublicProfileScreen(
                        state = state,
                        onInviteToAnEventClicked = {}, // TODO("Invite to event action")
                        onAllReviewsScreenClicked = { navController.navigate(Destinations.ALL_REVIEWS.route) },
                        onAllPlannedEventsScreenClicked = { navController.navigate(Destinations.ALL_PLANNED_EVENTS.route) },
                        paddingValues = it,
                    )
                }
            )
        }

        composable(Destinations.ALL_REVIEWS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value

            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    AllReviewsScreen(
                        state = state,
                        onLoadMoreReviews = { publicProfileViewModel.loadMoreReviews() },
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.ALL_PLANNED_EVENTS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value

            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    AllPlannedEventsScreen(
                        state = state,
                        onLoadMoreEvents = {
                        publicProfileViewModel.loadMoreEvents()
                    },
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) {
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileStartScreen(
                state = state,
                onFillingOutTheUserProfileStep1Clicked = {
                    navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE1.route)
                },
                onRemindMeLater = { /*TODO*/ })
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE1.route) {
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep1(
                state = state,
                onFillingOutTheUserProfileStep2Clicked = {navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE2.route)},
                onTurnBackClicked = {navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route)},
            )
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE2.route) {
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep2(
                state = state,
                onFillingOutTheUserProfileStep3Clicked = {navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE3.route)},
                onTurnBackClicked = {navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE1.route)},
            )
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE3.route) {
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep3(
                state = state,
                onFillingOutTheUserProfileStep4Clicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE4.route) },
                onTurnBackClicked = {navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE2.route)})
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE4.route) {
            val currentState = onboardingProfileViewModel.currentState
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep4(
                state = state,
                onFinishClicked = {
                    onboardingProfileViewModel.handleEvent(OnboardingScreensStatesMainContract.Event.FinishFillingOutTheProfileClicked)
                },
                onTurnBackClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE3.route) })

            LaunchedEffect(key1 = currentState.isSuccessRequestToFinishOutTheProfile.value) {
                if (currentState.isSuccessRequestToFinishOutTheProfile.value) {
                    currentState.isSuccessRequestToFinishOutTheProfile.value = false
                    navController.navigate(Destinations.PUBLIC_PROFILE.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(Destinations.USER_TRAINING_1.route) {
            UserTrainingStep1(
                onTrainingUserStep2Clicked = { navController.navigate(Destinations.USER_TRAINING_2.route) },
                onSkipButtonClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) }
            )
        }

        composable(Destinations.USER_TRAINING_2.route) {
            UserTrainingStep2(
                onTrainingUserStep3Clicked = { navController.navigate(Destinations.USER_TRAINING_3.route) },
                onSkipButtonClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) }

            )
        }

        composable(Destinations.USER_TRAINING_3.route) {
            UserTrainingStep3(
                onTrainingUserStep4Clicked = { navController.navigate(Destinations.USER_TRAINING_4.route) },
                onSkipButtonClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) }
            )
        }

        composable(Destinations.USER_TRAINING_4.route) {
            UserTrainingStep4(
                onFillingOutTheUserProfileStartScreenClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) },
                onSkipButtonClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) }
            )
        }

        composable(BottomNavItem.Home.screen_route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    HomeScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(BottomNavItem.FutureEvents.screen_route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    FutureEventsScreen(
                        paddingValues = it
                    )
                }
            )
        }
        composable(BottomNavItem.CreateNewEvent.screen_route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    CreateNewEventScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(BottomNavItem.Rating.screen_route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    RatingScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(BottomNavItem.Chat.screen_route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    ChatsScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.FRIENDS.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    FriendsScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.PLANNED_EVENTS.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    PlannedEventsScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.NOTIFICATIONS.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    NotificationsScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.SETTINGS.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    SettingsScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.MY_PROFILE.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    MyProfileScreen(
                        paddingValues = it
                    )
                }
            )
        }

        composable(Destinations.VERSIONS.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    VersionsScreen(
                        paddingValues = it
                    )
                }
            )
        }
    }
}