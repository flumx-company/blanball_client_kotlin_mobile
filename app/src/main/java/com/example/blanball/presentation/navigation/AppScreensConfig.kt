package com.example.blanball.presentation.navigation

import Destinations
import PublicProfileScreen
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.blanball.presentation.data.EmailVerificationMainContract
import com.example.blanball.presentation.data.EventEditAndCreationScreensMainContract
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.MyEventsScreenMainContract
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.SelectLocationScreenMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.viewmodels.EmailVerificationViewModel
import com.example.blanball.presentation.viewmodels.EventCreationOrEditScreensViewModel
import com.example.blanball.presentation.viewmodels.EventScreenViewModel
import com.example.blanball.presentation.viewmodels.FoundAnErrorViewModel
import com.example.blanball.presentation.viewmodels.FutureEventsScreenViewModel
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.MyEventsScreenViewModel
import com.example.blanball.presentation.viewmodels.MyProfileScreenViewModel
import com.example.blanball.presentation.viewmodels.NavigationDrawerViewModel
import com.example.blanball.presentation.viewmodels.OnboardingProfileViewModel
import com.example.blanball.presentation.viewmodels.PublicProfileViewModel
import com.example.blanball.presentation.viewmodels.RegistrationViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.viewmodels.SelectLocationScreenViewModel
import com.example.blanball.presentation.viewmodels.UsersRatingViewModel
import com.example.blanball.presentation.views.components.bottomnavbars.BottomNavBar
import com.example.blanball.presentation.views.components.cards.ConfirmEmailReminder
import com.example.blanball.presentation.views.components.drawers.InvitedUsersBottomDrawer
import com.example.blanball.presentation.views.components.drawers.NavigationDrawer
import com.example.blanball.presentation.views.components.drawers.PreviewOfTheEventBottomDrawer
import com.example.blanball.presentation.views.components.modals.EmailVerificationModal
import com.example.blanball.presentation.views.components.modals.ShareAnEventModal
import com.example.blanball.presentation.views.components.topbars.TopBar
import com.example.blanball.presentation.views.screens.chats.ChatsScreen
import com.example.blanball.presentation.views.screens.event.EventScreen
import com.example.blanball.presentation.views.screens.eventcreationoredit.EventEditOrCreationScreenStep1
import com.example.blanball.presentation.views.screens.eventcreationoredit.EventEditOrCreationScreenStep2
import com.example.blanball.presentation.views.screens.eventcreationoredit.EventEditOrCreationScreenStep3
import com.example.blanball.presentation.views.screens.foundanerror.FoundAnErrorScreen
import com.example.blanball.presentation.views.screens.friends.FriendsScreen
import com.example.blanball.presentation.views.screens.futureevents.AllEventsFilterScreen
import com.example.blanball.presentation.views.screens.futureevents.FutureEventsScreen
import com.example.blanball.presentation.views.screens.home.HomeScreen
import com.example.blanball.presentation.views.screens.login.LoginScreen
import com.example.blanball.presentation.views.screens.myevents.MyEventsFilterScreen
import com.example.blanball.presentation.views.screens.myevents.MyEventsScreen
import com.example.blanball.presentation.views.screens.myprofile.EditMyProfileScreen
import com.example.blanball.presentation.views.screens.myprofile.MyProfilePreviewScreen
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
import com.example.blanball.presentation.views.screens.rating.RatingScreen
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep1
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep2
import com.example.blanball.presentation.views.screens.resset.NewPasswordSuccessfullySavedScreen
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep1
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep3
import com.example.blanball.presentation.views.screens.selectlocation.SelectLocationScreen
import com.example.blanball.presentation.views.screens.settings.SettingsScreen
import com.example.blanball.presentation.views.screens.versions.VersionsScreen
import com.example.data.datastore.emailverificationmanager.EmailVerificationManager
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.useremailmanager.UserEmailManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import com.example.domain.utils.Endpoints
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
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
    usersRatingViewModel: UsersRatingViewModel,
    startDestinations: String,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    rememberMeManager: RememberMeManager,
    tokenManager: TokenManager,
    userNameManager: UserNameManager,
    userAvatarUrlManager: UserAvatarUrlManager,
    userPhoneManager: UserPhoneManager,
    resetPassVerifyCodeManager: ResetPassVerifyCodeManager,
    foundAnErrorViewModel: FoundAnErrorViewModel,
    myProfileScreenViewModel: MyProfileScreenViewModel,
    eventCreationScreenViewModel: EventCreationOrEditScreensViewModel,
    futureEventsScreenViewModel: FutureEventsScreenViewModel,
    myEventsViewModel: MyEventsScreenViewModel,
    eventScreenViewModel: EventScreenViewModel,
    emailVerificationViewModel: EmailVerificationViewModel,
    userEmailManager: UserEmailManager,
    eventCreationOrEditViewModel: EventCreationOrEditScreensViewModel,
    emailVerificationManager: EmailVerificationManager,
    selectLocationScreenViewModel: SelectLocationScreenViewModel,
) {
    val eventCreationOrEditUiState = eventCreationOrEditViewModel.uiState.collectAsState().value
    val publicProfileCurrentState = publicProfileViewModel.currentState
    val emailVerificationVMCurrentState = emailVerificationViewModel.currentState
    fun openNavDrawer() {
        coroutineScope.launch {
            scaffoldState.drawerState.open()
        }
    }

    fun closeNavDrawer() {
        coroutineScope.launch {
            delay(200)
            scaffoldState.drawerState.close()
        }
    }

    val bottomDrawerContent: @Composable () -> Unit = {
        val bottomPreviewDrawerState = rememberModalBottomSheetState()
        val eventCreationOrEditUiState = eventCreationOrEditViewModel.uiState.collectAsState().value
        PreviewOfTheEventBottomDrawer(
            bottomDrawerState = bottomPreviewDrawerState,
            state = eventCreationOrEditUiState
        )
    }

    val invitedUsersDrawerContent: @Composable () -> Unit = {
        InvitedUsersBottomDrawer(
            state = eventCreationOrEditUiState,
        )
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
                    resetPassVerifyCodeManager.deleteResetPassVerifyCode()
                    userEmailManager.deleteUserEmail()
                    emailVerificationManager.deleteIsEmailVerifiedState()
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
                    navigationDrawerViewModel.currentState.isSplashScreenActivated.value = true
                    navController.navigate(Destinations.HOME.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
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
            val state = publicProfileViewModel.uiState.collectAsState().value

            LaunchedEffect(key1 = Unit) {
                publicProfileViewModel.loadUserProfileData()
            }
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController,

                        )
                },
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                        }
                        PublicProfileScreen(
                            state = state,
                            onInviteToAnEventClicked = {}, // TODO("Invite to event action")
                            onAllReviewsScreenClicked = { navController.navigate(Destinations.ALL_REVIEWS.route) },
                            onAllPlannedEventsScreenClicked = { navController.navigate(Destinations.ALL_PLANNED_EVENTS.route) },
                        )
                    }
                },
            )
        }

        composable(Destinations.ALL_REVIEWS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        AllReviewsScreen(
                            state = state,
                            onLoadMoreReviews = { publicProfileViewModel.loadMoreReviews() },
                            paddingValues = paddingValues
                        )
                    }
                }
            )
        }

        composable(Destinations.ALL_PLANNED_EVENTS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
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
                onRemindMeLater = {
                    navigationDrawerViewModel.currentState.isSplashScreenActivated.value = true
                    navController.navigate(Destinations.HOME.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
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
                onTurnBackClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE2.route) })
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
                    navigationDrawerViewModel.currentState.isSplashScreenActivated.value = true
                    navController.navigate(Destinations.HOME.route) {
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
            val navigationDrawerCurrentState = navigationDrawerViewModel.currentState
            val futureEventsScreenViewModelState =
                futureEventsScreenViewModel.uiState.collectAsState().value
            val eventScreenViewModelCurrentState = eventScreenViewModel.currentState
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        HomeScreen(
                            onNavigateToEvent = { eventId ->
                                eventScreenViewModelCurrentState.currentEventId.value = eventId
                                navController.navigate(Destinations.EVENT.route)
                            },
                            state = futureEventsScreenViewModelState,
                            onLoadMoreUsers = { futureEventsScreenViewModel.loadMoreAllEvents() },
                            userFirstName = navigationDrawerCurrentState.userFirstNameText.value,
                        )
                    }
                }
            )
        }

        composable(BottomNavItem.FutureEvents.screen_route) {
            val futureEventScreenCurrentState = futureEventsScreenViewModel.currentState
            val futureEventsScreenViewModelState =
                futureEventsScreenViewModel.uiState.collectAsState().value
            val previousState by remember { mutableStateOf(futureEventScreenCurrentState.state) }
            val eventScreenViewModelCurrentState = eventScreenViewModel.currentState
            LaunchedEffect(futureEventScreenCurrentState.state != previousState) {
                futureEventsScreenViewModel.handleScreenState(futureEventScreenCurrentState.state)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        FutureEventsScreen(
                            state = futureEventsScreenViewModelState,
                            paddingValues = paddingValues,
                            navigateToEventScreen = { eventId ->
                                eventScreenViewModelCurrentState.currentEventId.value = eventId
                                navController.navigate(Destinations.EVENT.route)
                            },
                            onClickedToChangeOrdering = {
                                futureEventsScreenViewModel.setState {
                                    copy(
                                        state = FutureEventsMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            onLoadMoreUsers = { futureEventsScreenViewModel.loadMoreAllEvents() },
                            navigateToCreationEventScreen = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_1.route) },
                            navigateToFilterScreen = { navController.navigate(Destinations.ALL_EVENTS_FILTER_SCREEN.route) },
                            navigateToMyEventsScreen = { navController.navigate(Destinations.MY_EVENTS.route) },
                        )
                    }
                }
            )
        }

        composable(BottomNavItem.CreateNewEvent.screen_route) {
            val isDatePickerModalVisible = remember { mutableStateOf(false) }
            val currentState = eventCreationScreenViewModel.currentState
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep1(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            navigateToSecondStep = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_2.route) },
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            backBtnCLicked = {
                                navController.navigate(Destinations.HOME.route)
                            },
                            onSelectLocationScreenNav = {
                                navController.navigate(Destinations.SELECT_LOCATION.route)
                            },
                        )
                    }
                }
            )
        }

        composable(BottomNavItem.Rating.screen_route) {

            val state = usersRatingViewModel.uiState.collectAsState().value
            val ratingCurrentState = usersRatingViewModel.currentState

            val previousState by remember { mutableStateOf(ratingCurrentState.state) }

            LaunchedEffect(ratingCurrentState.state != previousState) {
                usersRatingViewModel.handleScreenState(ratingCurrentState.state)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        RatingScreen(
                            state = state,
                            onLoadMoreUsers = {
                                usersRatingViewModel.loadMoreUsers()
                            },
                            onClickedToLoadWithNewFilters = {
                                usersRatingViewModel.setState {
                                    copy(
                                        openFiltersDialog = mutableStateOf(false),
                                        state = RatingUsersMainContract.ScreenViewState.Loading,
                                    )
                                }
                            },
                            onClickedToChangeOrdering = {
                                usersRatingViewModel.setState {
                                    copy(
                                        state = RatingUsersMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            onClickedToCleanFiters = {
                                usersRatingViewModel.setState {
                                    copy(
                                        openFiltersDialog = mutableStateOf(false),
                                        genderSelectionState = mutableStateOf(
                                            RatingUsersMainContract.GenderSelectionState.ALL
                                        ),
                                        ageSliderPosition = mutableStateOf(6f..80f),
                                        gamePositionSelectionState = mutableStateOf(
                                            RatingUsersMainContract.GamePositionSelectionState.ALL
                                        ),
                                        positionSelectedItem = mutableStateOf(""),
                                        state = RatingUsersMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            onClickedToPublicProfile = { userId ->
                                publicProfileCurrentState.userId.value = userId
                                navController.navigate(Destinations.PUBLIC_PROFILE.route)
                            },
                            paddingValues = paddingValues
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        ChatsScreen(
                            paddingValues = paddingValues
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        FriendsScreen(
                            paddingValues = paddingValues
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                        }
                        PlannedEventsScreen(
                            paddingValues = paddingValues
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                        }
                        NotificationsScreen(
                            paddingValues = paddingValues
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        SettingsScreen(
                            paddingValues = paddingValues
                        )
                    }
                }
            )
        }

        composable(Destinations.MY_PROFILE.route) {
            val myProfileScreenState = myProfileScreenViewModel.uiState.collectAsState().value

            LaunchedEffect(key1 = Unit, block = {
                myProfileScreenViewModel.handleScreenState(MyProfileScreensMainContract.Event.SendGetMyProfileRequest)
            }) //TODO

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        MyProfileScreen(
                            state = myProfileScreenState,
                            paddingValues = paddingValues,
                            editProfileButtonClicked = { navController.navigate(Destinations.EDIT_PROFILE.route) },
                            exitBtnClicked = {
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
                                    resetPassVerifyCodeManager.deleteResetPassVerifyCode()
                                }
                            },
                            deleteAccBtnClicked = {}
                        )
                    }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                }, content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        VersionsScreen(
                            paddingValues = paddingValues
                        )
                    }
                }
            )
        }

        composable(
            route = Destinations.EVENT.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${Endpoints.WEB_DOMAIN}${Endpoints.DOMAIN_EVENTS_PATH}{id}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val eventScreenViewModelCurrentState = eventScreenViewModel.currentState
            val eventId = entry.arguments?.getInt("id")
            if (eventId != 0) {
                eventScreenViewModelCurrentState.currentEventId.value = eventId
            }
            val verifyEmailViewModelState =
                emailVerificationViewModel.uiState.collectAsState().value

            val eventScreenViewModelState = eventScreenViewModel.uiState.collectAsState().value
            val isVerificationModalVisible = remember { mutableStateOf(false) }
            val isShareLinkModalVisible =
                remember { mutableStateOf(false) } //TODO  Need move this states to screnn view model
            val verifyEmailViewModeCurrentState = emailVerificationViewModel.currentState
            LaunchedEffect(key1 = Unit) {
                eventScreenViewModel.loadUEventData() //TODO() Make it encapsulated - without calling the method directly
            }
            LaunchedEffect(verifyEmailViewModeCurrentState.isEmailVerifySuccess.value) {
                if (verifyEmailViewModeCurrentState.isEmailVerifySuccess.value) {
                    verifyEmailViewModeCurrentState.isEmailVerifySuccess.value = false
                    isVerificationModalVisible.value = false
                }
            }

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                        }
                        EventScreen(
                            state = eventScreenViewModelState,
                            paddingValues = paddingValues,
                            isVerificationModalVisible = isVerificationModalVisible,
                            verificationModalScreenContent = {
                                LaunchedEffect(key1 = Unit) {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    val userEmail =
                                        emailVerificationViewModel.currentState.userEmailText.value
                                    emailVerificationViewModel.setState {
                                        copy(
                                            userEmailText = mutableStateOf(userEmail ?: ""),
                                        )
                                    }
                                }
                                EmailVerificationModal(
                                    state = verifyEmailViewModelState,
                                    turnBackBtnClicked = {
                                        isVerificationModalVisible.value = false
                                        emailVerificationViewModel.setState {
                                            copy(
                                                codeText = List(5) { mutableStateOf("") }
                                            )
                                        }
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                )
                            },
                            isShareLinkModalVisible = isShareLinkModalVisible,
                            shareLinkModalScreenContent = {
                                eventScreenViewModelCurrentState.currentEventId.value?.let { id ->
                                    ShareAnEventModal(
                                        backBtnClicked = { isShareLinkModalVisible.value = false },
                                        currentEventId = id,
                                    )
                                }
                            },
                            onNavigateToEventAuthorPublicProfile = {
                                publicProfileCurrentState.userId.value =
                                    eventScreenViewModelCurrentState.currentEventAuthorId.value
                                navController.navigate(Destinations.PUBLIC_PROFILE.route)
                            },
                            isConfirmReminderVisible = verifyEmailViewModeCurrentState.isEmailVerified.value,
                            isConfirmReminderContent = {
                            },
                            onEditClick = { currentEventId ->
                                eventCreationOrEditViewModel.currentState.currentEventId.value = currentEventId
                                navController.navigate(
                                    Destinations.EDIT_EVENT_STEP_1.route
                                )
                            }
                        )
                    }
                }
            )
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        FoundAnErrorScreen(
                            state = state,
                            paddingValues = paddingValues,
                            closeButtonClicked = { navController.navigate(Destinations.HOME.route) }
                        )
                    }
                }
            )
        }

        composable(Destinations.CREATE_NEW_EVENT_STEP_2.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep2(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            navigateToThirdStep = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_3.route) },
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            backBtnCLicked = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_1.route) },
                            usersSearchClicked = {
                                eventCreationScreenViewModel.handleEvent(
                                    EventEditAndCreationScreensMainContract.Event.UsersSearchClicked
                                )
                            },
                            isEditOrCreation = EventEditAndCreationScreensMainContract.EditOrCreationState.CREATION,
                        )
                    }
                }
            )
        }

        composable(Destinations.CREATE_NEW_EVENT_STEP_3.route) {
            val currentState = eventCreationScreenViewModel.currentState
            LaunchedEffect(key1 = Unit) {
                val userPhoneString = userPhoneManager.getUserPhone().firstOrNull().toString()
                eventCreationScreenViewModel.setState {
                    copy(
                        phoneNumberState = mutableStateOf(
                            userPhoneString
                        )
                    )
                }
            }
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep3(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            publishBtnClicked = {
                                eventCreationScreenViewModel.handleEvent(
                                    EventEditAndCreationScreensMainContract.Event.CreateNewEventClicked
                                )
                            },
                            backBtnCLicked = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_2.route) },
                            isEditOrCreation = EventEditAndCreationScreensMainContract.EditOrCreationState.CREATION,
                        )
                    }
                }
            )
            LaunchedEffect(currentState.isSuccessEventCreation.value) {
                if (currentState.isSuccessEventCreation.value) {
                    currentState.isSuccessEventCreation.value = false
                   futureEventsScreenViewModel.currentState.isShowEventSuccessCreatedModal.value = true
                    navController.navigate(Destinations.HOME.route)
                }
            }
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
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EditMyProfileScreen(
                            state = state,
                            paddingValues = paddingValues,
                            onBackClicked = { navController.navigate(Destinations.MY_PROFILE.route) },
                            onNavigateToDemoClicked = { navController.navigate(Destinations.MY_PROFILE_PREVIEW_SCREEN.route) },
                            onSimpleSaveClicked = {
                                myProfileScreenViewModel.handleScreenState(
                                    MyProfileScreensMainContract.Event.SendEditMyProfileRequest
                                )
                                navController.navigate(Destinations.MY_PROFILE.route)
                            },
                            onCancelEditsClicked = {
                                navController.navigate(Destinations.MY_PROFILE.route)
                            }
                        )
                    }
                }
            )
        }

        composable(Destinations.MY_EVENTS.route) {
            val state = myEventsViewModel.uiState.collectAsState().value
            val myEventsScreenCurrentState = myEventsViewModel.currentState
            val previousState by remember { mutableStateOf(myEventsScreenCurrentState.state) }

            LaunchedEffect(myEventsScreenCurrentState.state != previousState) {
                myEventsViewModel.handleScreenState(myEventsScreenCurrentState.state)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        MyEventsScreen(
                            state = state,
                            paddingValues = paddingValues,
                            navigateToEventScreen = { eventId ->
                                val eventScreenViewModelCurrentState =
                                    eventScreenViewModel.currentState
                                eventScreenViewModelCurrentState.currentEventId.value = eventId
                                navController.navigate(Destinations.EVENT.route)
                            },
                            onLoadMoreUsers = { myEventsViewModel.loadMoreMyEvents() },
                            navigateToAllEventsScreen = { navController.navigate(Destinations.FUTURE_EVENTS.route) },
                            navigateToMyEventsFilterScreen = { navController.navigate(Destinations.MY_EVENTS_FILTER_SCREEN.route) },
                            onClickedToChangeOrdering = {
                                myEventsViewModel.setState {
                                    copy(
                                        state = MyEventsScreenMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            onCreatedEventClicked = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_1.route) },
                        )
                    }
                }
            )
        }


        composable(Destinations.ALL_EVENTS_FILTER_SCREEN.route) {
            val state = futureEventsScreenViewModel.uiState.collectAsState().value

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        AllEventsFilterScreen(
                            state = state,
                            turnBackBtnClicked = {
                                navController.navigate(Destinations.FUTURE_EVENTS.route)
                                futureEventsScreenViewModel.setState {
                                    copy(
                                        openFiltersDialog = mutableStateOf(false),
                                        gendersSelectionState = mutableStateOf(
                                            FutureEventsMainContract.GenderSelectionState.ALL
                                        ),
                                        typeOfSportsStateSelected = mutableStateOf(""),
                                        eventDatesState = mutableStateOf(""),
                                        filterDateAndTimeAfter = mutableStateOf(""),
                                        filterDateAndTimeBefore = mutableStateOf(""),
                                        state = FutureEventsMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            paddingValues = paddingValues,
                            confirmBtnClicked = {
                                navController.navigate(Destinations.FUTURE_EVENTS.route)
                                futureEventsScreenViewModel.setState {
                                    copy(
                                        state = FutureEventsMainContract.ScreenViewState.Loading
                                    )
                                }
                            }
                        )
                    }
                }
            )
        }

        composable(Destinations.MY_EVENTS_FILTER_SCREEN.route) {
            val state = myEventsViewModel.uiState.collectAsState().value

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        MyEventsFilterScreen(
                            state = state,
                            clearBtnClicked = {
                                navController.navigate(Destinations.FUTURE_EVENTS.route)
                                myEventsViewModel.setState {
                                    copy(
                                        openFiltersDialog = mutableStateOf(false),
                                        gendersSelectionState = mutableStateOf(
                                            MyEventsScreenMainContract.GenderSelectionState.ALL
                                        ),
                                        filterDateAndTimeBefore = mutableStateOf(""),
                                        filterDateAndTimeAfter = mutableStateOf(""),
                                        typeOfSportsStateSelected = mutableStateOf(""),
                                        eventDatesState = mutableStateOf(""),
                                        state = MyEventsScreenMainContract.ScreenViewState.Loading
                                    )
                                }
                            },
                            paddingValues = paddingValues,
                            confirmBtnClicked = {
                                navController.navigate(Destinations.FUTURE_EVENTS.route)
                                myEventsViewModel.setState {
                                    copy(
                                        state = MyEventsScreenMainContract.ScreenViewState.Loading
                                    )
                                }
                            }
                        )
                    }
                }
            )
        }

        composable(Destinations.MY_PROFILE_PREVIEW_SCREEN.route) {

            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    MyProfilePreviewScreen(
                        state = myProfileScreenViewModel.currentState,
                        paddingValues = paddingValues,
                        onBackClicked = {
                            navController.navigate(Destinations.EDIT_PROFILE.route)
                        },
                        onSaveClicked = {
                            navController.navigate(Destinations.MY_PROFILE.route)
                            myProfileScreenViewModel.handleScreenState(MyProfileScreensMainContract.Event.SendEditMyProfileRequest)
                        }
                    )
                }
            )
        }

        composable(Destinations.EDIT_EVENT_STEP_1.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep1(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            navigateToSecondStep = { navController.navigate(Destinations.EDIT_EVENT_STEP_2.route) },
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            backBtnCLicked = {
                                navController.navigate(Destinations.HOME.route)
                            },
                            onSelectLocationScreenNav = {navController.navigate(Destinations.SELECT_LOCATION.route)},

                        )
                    }
                }
            )
        }

        composable(Destinations.EDIT_EVENT_STEP_2.route) {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep2(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            navigateToThirdStep = { navController.navigate(Destinations.EDIT_EVENT_STEP_3.route) },
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            backBtnCLicked = { navController.navigate(Destinations.EDIT_EVENT_STEP_1.route) },
                            usersSearchClicked = {
                                eventCreationScreenViewModel.handleEvent(
                                    EventEditAndCreationScreensMainContract.Event.UsersSearchClicked
                                )
                            },
                            isEditOrCreation = EventEditAndCreationScreensMainContract.EditOrCreationState.EDIT,
                        )
                    }
                }
            )
        }

        composable(Destinations.EDIT_EVENT_STEP_3.route) {
            val currentState = eventCreationScreenViewModel.currentState
            LaunchedEffect(key1 = Unit) {
                val userPhoneString = userPhoneManager.getUserPhone().firstOrNull().toString()
                eventCreationScreenViewModel.setState {
                    copy(
                        phoneNumberState = mutableStateOf(
                            userPhoneString
                        )
                    )
                }
            }
            Scaffold(
                scaffoldState = scaffoldState,
                drawerContent = navDrawerContent,
                drawerShape = RoundedCornerShape(0.dp),
                drawerBackgroundColor = backgroundItems,
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        navController = navController,
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                            if (emailVerificationVMCurrentState.isVerificationModalVisible.value) {
                                EmailVerificationModal(
                                    state = emailVerificationViewModel.uiState.collectAsState().value,
                                    resendCodeToEmailClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                        )
                                    },
                                    turnBackBtnClicked = {
                                        emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                            false
                                    },
                                    confirmBtnClicked = {
                                        emailVerificationViewModel.handleEvent(
                                            EmailVerificationMainContract.Event.VerifyEmailClicked
                                        )
                                    },
                                )
                            }
                        }
                        EventEditOrCreationScreenStep3(
                            paddingValues = paddingValues,
                            state = eventCreationOrEditUiState,
                            bottomDrawerPreviewContent = { bottomDrawerContent() },
                            invitedUsersModalContent = { invitedUsersDrawerContent() },
                            publishBtnClicked = {
                                eventCreationScreenViewModel.handleEvent(
                                    EventEditAndCreationScreensMainContract.Event.EditEventClicked
                                )
                            },
                            backBtnCLicked = { navController.navigate(Destinations.CREATE_NEW_EVENT_STEP_2.route) },
                            isEditOrCreation = EventEditAndCreationScreensMainContract.EditOrCreationState.EDIT,
                        )
                    }
                }
            )
            LaunchedEffect(currentState.isSuccessEventCreation.value) {
                if (currentState.isSuccessEventCreation.value) {
                    currentState.isSuccessEventCreation.value = false
                    navController.navigate(Destinations.HOME.route)
                }
            }
        }

        composable(Destinations.SELECT_LOCATION.route) {
            val state = selectLocationScreenViewModel.uiState.collectAsState().value

            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController,

                        )
                },
                topBar = {
                    TopBar(
                        navController = navController,
                        onNavIconClicked = { openNavDrawer() },
                    )
                },
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        if (!emailVerificationViewModel.currentState.isEmailVerified.value) {
                            ConfirmEmailReminder(
                                clickCallback = {
                                    emailVerificationViewModel.handleEvent(
                                        EmailVerificationMainContract.Event.SendCodeToUserEmailClicked
                                    )
                                    emailVerificationVMCurrentState.isVerificationModalVisible.value =
                                        true
                                },
                                userEmail = emailVerificationViewModel.currentState.userEmailText.value
                            )
                        }
                        SelectLocationScreen(
                            onCancelClicked = { navController.navigateUp() },
                            onSaveLocationClicked = { navController.navigateUp() },
                            eventLocationLatLng = eventCreationOrEditViewModel.currentState.eventLocationLatLng,
                            selectRegion = eventCreationOrEditViewModel.currentState.selectRegion,
                            selectCity = eventCreationOrEditViewModel.currentState.selectCity,
                            state = state,
                            onUpdateCitiesForRegionList ={
                                selectLocationScreenViewModel.handleEvent(SelectLocationScreenMainContract.Event.UpdateCitiesForRegionList)
                            } ,
                        )
                    }
                },
            )
        }

    }
}