package com.example.blanball.presentation.navigation

import Destinations
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.viewmodels.EventCreationScreensViewModel
import com.example.blanball.presentation.viewmodels.FoundAnErrorViewModel
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.MyProfileScreenViewModel
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.viewmodels.OnboardingProfileViewModel
import com.example.blanball.presentation.viewmodels.PublicProfileViewModel
import com.example.blanball.presentation.viewmodels.RegistrationViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.views.components.bottomnavbars.BottomNavBar
import com.example.blanball.presentation.views.components.drawers.InvitedUsersBottomDrawer
import com.example.blanball.presentation.views.components.drawers.NavigationDrawer
import com.example.blanball.presentation.views.components.drawers.PreviewOfTheEventBottomDrawer
import com.example.blanball.presentation.views.components.modals.EmailVerificationModal
import com.example.blanball.presentation.views.components.modals.ShareAnEventModal
import com.example.blanball.presentation.views.components.textinputs.SimpleDatePickerInDatePickerDialog
import com.example.blanball.presentation.views.components.topbars.TopBar
import com.example.blanball.presentation.views.screens.chats.ChatsScreen
import com.example.blanball.presentation.views.screens.event.EventScreen
import com.example.blanball.presentation.views.screens.eventcreation.EventCreationScreenStep1
import com.example.blanball.presentation.views.screens.eventcreation.EventCreationScreenStep2
import com.example.blanball.presentation.views.screens.eventcreation.EventCreationScreenStep3
import com.example.blanball.presentation.views.screens.foundanerror.FoundAnErrorScreen
import com.example.blanball.presentation.views.screens.friends.FriendsScreen
import com.example.blanball.presentation.views.screens.futureevents.FutureEventsScreen
import com.example.blanball.presentation.views.screens.home.HomeScreen
import com.example.blanball.presentation.views.screens.login.LoginScreen
import com.example.blanball.presentation.views.screens.myprofile.EditMyProfileScreen
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

@OptIn(ExperimentalMaterial3Api::class)
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
    userNameManager: UserNameManager,
    userAvatarUrlManager: UserAvatarUrlManager,
    userPhoneManager: UserPhoneManager,
    verifyCodeManager: VerifyCodeManager,
    foundAnErrorViewModel: FoundAnErrorViewModel,
    myProfileScreenViewModel: MyProfileScreenViewModel,
    eventCreationScreenViewModel: EventCreationScreensViewModel,
) {
    val openNavDrawer: () -> Unit = {
        coroutineScope.launch {
            scaffoldState.drawerState.open()
        }
    }
    val closeNavDrawer: () -> Unit = {
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
                closeNavDrawer()
                navController.navigate(Destinations.FRIENDS.route)
            },
            onPlannedEventsScreenClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.PLANNED_EVENTS.route)
            },
            onNotificationsScreenClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.NOTIFICATIONS.route)
            },
            onSettingsScreenClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.SETTINGS.route)
            },
            onMyProfileScreenClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.MY_PROFILE.route)
            },
            onVersionsScreenClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.VERSIONS.route)
            },
            onFoundAnErrorClicked = {
                closeNavDrawer()
                navController.navigate(Destinations.FOUND_AN_ERROR.route)
            },
            onLogOutClicked = {
                closeNavDrawer()
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

    val bottomPreviewDrawerState = rememberModalBottomSheetState()
    val isBottomPreviewDrawerOpen: MutableState<Boolean> = remember { mutableStateOf(false) }

    val bottomDrawerContent: @Composable () -> Unit = {
        PreviewOfTheEventBottomDrawer(bottomDrawerState = bottomPreviewDrawerState, closeBottomDrawer = { isBottomPreviewDrawerOpen.value = false })
    }

    val openBottomDrawer: () -> Unit = {
        coroutineScope.launch {
            bottomPreviewDrawerState.expand()
        }
    }
    val closeBottomDrawer: () -> Unit = {
        coroutineScope.launch {
            bottomPreviewDrawerState.hide()
        }
    }

    val invitedUsersDrawerState = rememberModalBottomSheetState()
    val isInvitedUsersDrawerOpen: MutableState<Boolean> = remember { mutableStateOf(false) }

    val invitedUsersDrawerContent: @Composable () -> Unit = {
        InvitedUsersBottomDrawer(bottomDrawerState = invitedUsersDrawerState, closeBottomDrawer = { isInvitedUsersDrawerOpen.value = false})
    }

    val openInvitedUsersDrawerDrawer: () -> Unit = {
        coroutineScope.launch {
            invitedUsersDrawerState.expand()
        }
    }

    val closeInvitedUsersDrawer: () -> Unit = {
        coroutineScope.launch {
            invitedUsersDrawerState.hide()
        }
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
                    resetPassViewModel.setState {
                        copy(
                            resetEmailText = mutableStateOf(""),
                            codeText = List(5) { mutableStateOf("") },
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
                    resetPassViewModel.setState {
                        copy(
                            resetEmailText = mutableStateOf(""),
                            codeText = List(5) { mutableStateOf("") },
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
                    resetPassViewModel.setState {
                        copy(
                            resetEmailText = mutableStateOf(""),
                            codeText = List(5) { mutableStateOf("") },
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

        composable(Destinations.RESET_COMPLETE.route) {

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
                    navController.navigate(Destinations.USER_TRAINING_1.route) {
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
                onFillingOutTheUserProfileStep2Clicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE2.route) },
                onTurnBackClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route) },
            )
        }

        composable(Destinations.FILLING_OUT_THE_USER_PROFILE2.route) {
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep2(
                state = state,
                onFillingOutTheUserProfileStep3Clicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE3.route) },
                onTurnBackClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE1.route) },
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
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    HomeScreen(
                        paddingValues = it,
                        navigateToEvent = {}, //TODO
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
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    FutureEventsScreen(paddingValues = it,
                        navigateToEventScreen = { navController.navigate(Destinations.EVENT.route) })
                }
            )
        }
        composable(BottomNavItem.CreateNewEvent.screen_route) {
            val isDatePickerModalVisible = remember { mutableStateOf(false) }
            val state = eventCreationScreenViewModel.uiState.collectAsState().value
            val currentState = eventCreationScreenViewModel.currentState
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    EventCreationScreenStep1(
                        paddingValues = it,
                        state = state,
                        navigateToSecondStep = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_2.route) },
                        bottomDrawerPreviewContent = { bottomDrawerContent() },
                        isBottomDrawerOpen = isBottomPreviewDrawerOpen,
                        isDatePickerModalOpen = isDatePickerModalVisible,
                        invitedUsersModalContent = { invitedUsersDrawerContent() },
                        isInvitedUsersModalOpen = isInvitedUsersDrawerOpen ,
                        datePickerModalContent = {
                            SimpleDatePickerInDatePickerDialog(
                                selectedState = currentState.eventDateState,
                                backBtnClicked = {isDatePickerModalVisible.value = false}
                            )
                        }
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
                        onNavIconClicked = openNavDrawer,
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
                        onNavIconClicked = openNavDrawer,
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
                        onNavIconClicked = openNavDrawer,
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
                        onNavIconClicked = openNavDrawer,
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
                        onNavIconClicked = openNavDrawer,
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
                        onNavIconClicked = openNavDrawer,
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
            val myProfileScreenState = myProfileScreenViewModel.uiState.collectAsState().value
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    MyProfileScreen(
                        state = myProfileScreenState,
                        paddingValues = it,
                        editProfileButtonClicked = { navController.navigate(Destinations.EDIT_PROFILE.route) },
                        exitBtnClicked = {},
                        deleteAccBtnClicked = {}
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
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                }, content = { it ->
                    VersionsScreen(
                        paddingValues = it
                    )
                })
        }

        composable(Destinations.EVENT.route) {
            val resetState = resetPassViewModel.uiState.collectAsState().value
            var isVerificationModalVisible = remember { mutableStateOf(false) }
            var isShareLinkModalVisible = remember { mutableStateOf(false) }

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { it ->
                    EventScreen(paddingValues = it,
                        isVerificationModalVisible = isVerificationModalVisible,
                        verificationModalScreenContent = {
                            EmailVerificationModal( //TODO()
                                state = resetState,
                                turnBackBtnClicked = { isVerificationModalVisible.value = false },
                                confirmBtnClicked = {},
                                resendCodeToEmailClicked = {}
                            )
                        },
                        isShareLinkModalVisible = isShareLinkModalVisible,
                        shareLinkModalScreenContent = {
                            ShareAnEventModal(
                                copyLinkBtnClicked = { },
                                backBtnClicked = { isShareLinkModalVisible.value = false }
                            )
                        }
                    )
                })
        }

        composable(Destinations.FOUND_AN_ERROR.route) {
            val state = foundAnErrorViewModel.uiState.collectAsState().value
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { paddingValues ->
                    FoundAnErrorScreen(
                        state = state,
                        paddingValues = paddingValues,
                        closeButtonClicked = { navController.navigate(Destinations.HOME.route) }
                    )
                }
            )
        }

        composable(Destinations.CREATE_NEW_EVENT_STEP_2.route) {
            val state = eventCreationScreenViewModel.uiState.collectAsState().value
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { paddingValues ->
                    EventCreationScreenStep2(
                        paddingValues = paddingValues,
                        state = state,
                        navigateToThirdStep = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_3.route) },
                        bottomDrawerPreviewContent = { bottomDrawerContent() },
                        isBottomDrawerOpen = isBottomPreviewDrawerOpen,
                        invitedUsersModalContent = { invitedUsersDrawerContent() },
                        isInvitedUsersModalOpen = isInvitedUsersDrawerOpen ,
                    )
                }
            )
        }

        composable(Destinations.CREATE_NEW_EVENT_STEP_3.route) {
            val state = eventCreationScreenViewModel.uiState.collectAsState().value
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { paddingValues ->
                    EventCreationScreenStep3(
                        paddingValues = paddingValues,
                        state = state,
                        bottomDrawerPreviewContent = { bottomDrawerContent() },
                        isBottomDrawerOpen = isBottomPreviewDrawerOpen,
                        invitedUsersModalContent = { invitedUsersDrawerContent() },
                        isInvitedUsersModalOpen = isInvitedUsersDrawerOpen,
                        publishBtnClicked = {

                        }
                    )
                }
            )
        }

        composable(Destinations.EDIT_PROFILE.route) {
            val state = myProfileScreenViewModel.uiState.collectAsState().value
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = openNavDrawer,
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
                content = { paddingValues ->
                    EditMyProfileScreen(
                        state = state,
                        paddingValues = paddingValues,
                        cancelBtnClicked = { /*TODO*/ },
                        saveBtnClicked = {},
                    )
                }
            )
        }
    }
}