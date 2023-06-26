package com.example.blanball.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.OnboardingProfileViewModel
import com.example.blanball.presentation.viewmodels.PublicProfileViewModel
import com.example.blanball.presentation.viewmodels.RegistrationViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.views.screens.login.LoginScreen
import com.example.blanball.presentation.views.screens.onboarding.FillingOutTheUserProfileScreenStep1
import com.example.blanball.presentation.views.screens.onboarding.FillingOutTheUserProfileScreenStep2
import com.example.blanball.presentation.views.screens.onboarding.FillingOutTheUserProfileScreenStep3
import com.example.blanball.presentation.views.screens.onboarding.FillingOutTheUserProfileScreenStep4
import com.example.blanball.presentation.views.screens.onboarding.FillingOutTheUserProfileStartScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllPlannedEventsScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllReviewsScreen
import com.example.blanball.presentation.views.screens.publicprofile.PublicProfileScreen
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep1
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep1
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep3

@Composable
fun AppScreensConfig(
    navController: NavHostController,
    resetPassViewModel: ResetPasswordViewModel,
    registrationViewModel: RegistrationViewModel,
    publicProfileViewModel: PublicProfileViewModel,
    loginViewModel: LoginViewModel,
    onboardingProfileViewModel: OnboardingProfileViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.FILLING_OUT_THE_USER_PROFILE_START.route
    )
    {
        composable(Destinations.LOGIN.route) {
            val state = loginViewModel.uiState.collectAsState().value
            val context = LocalContext.current

            LaunchedEffect(key1 = true) {
                loginViewModel.sideEffect.collect {
                    when (it) {
                        is StartScreensMainContract.Effect.ShowToast -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            LoginScreen(
                state = state,
                onLoginClicked = {
                    loginViewModel.handleEvent(StartScreensMainContract.Event.LoginClicked)
                },
                dontRememberButtonClicked = { navController.navigate(Destinations.RESET1.route) },
                registrationButtonClicked = { navController.navigate(Destinations.REGISTRATION1.route) })
        }

        composable(Destinations.RESET1.route) {
            val state = resetPassViewModel.uiState.collectAsState().value
            val currentState = resetPassViewModel.currentState

            ResetPasswordScreenStep1(
                state = state,
                onStep2Clicked = {
                    resetPassViewModel.handleEvent(StartScreensMainContract.Event.SendEmailResetRequestClicked)
                },
                onCancelClicked = { navController.navigate(Destinations.LOGIN.route) }
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
                onCancelClicked = { navController.navigate(Destinations.LOGIN.route) })

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
                onCancelClicked = { navController.navigate(Destinations.LOGIN.route) })

            LaunchedEffect(key1 = currentState.isSuccessCompleteResetState.value) {
                if (currentState.isSuccessCompleteResetState.value) {
                    currentState.isSuccessCompleteResetState.value = false
                    navController.navigate(Destinations.LOGIN.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(Destinations.REGISTRATION1.route) {
            val state = registrationViewModel.uiState.collectAsState().value
            RegistrationScreenStep1(
                state = state,
                onRegistrationStep2Clicked = { navController.navigate(Destinations.REGISTRATION2.route) },
                onCancelClicked = { navController.navigate(Destinations.LOGIN.route) })
        }

        composable(Destinations.REGISTRATION2.route) {
            val state = registrationViewModel.uiState.collectAsState().value
            val currentState = registrationViewModel.currentState

            RegistrationScreenStep2(
                state = state,
                onRegistrationClicked = {
                    registrationViewModel.handleEvent(StartScreensMainContract.Event.RegistrationClicked)
                    navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE_START.route)
                },
                onBackClicked = { navController.navigate(Destinations.REGISTRATION1.route) })

            LaunchedEffect(key1 = currentState.isSuccessRegistrationNewPass.value) {
                if (currentState.isSuccessRegistrationNewPass.value) {
                    currentState.isSuccessRegistrationNewPass.value = false
                    navController.navigate(Destinations.LOGIN.route){
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
               publicProfileViewModel.setState { copy(
                   state = PublicProfileMainContract.ScreenViewState.Loading
               ) }
           }

            PublicProfileScreen(
                state = state,
                onInviteToAnEventClicked = {}, // TODO("Invite to event action")
                onAllReviewsScreenClicked = { navController.navigate(Destinations.ALL_REVIEWS.route)},
                onAllPlannedEventsScreenClicked = { navController.navigate(Destinations.ALL_PLANNED_EVENTS.route)}
            )
        }

        composable(Destinations.ALL_REVIEWS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value
            AllReviewsScreen(
                state = state,
                onLoadMoreReviews = { publicProfileViewModel.loadMoreReviews() })
        }

        composable(Destinations.ALL_PLANNED_EVENTS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value
            AllPlannedEventsScreen(state = state, onLoadMoreEvents = {
                publicProfileViewModel.loadMoreEvents()
            })
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
            val state = onboardingProfileViewModel.uiState.collectAsState().value
            FillingOutTheUserProfileScreenStep4(
                state = state,
                onFinishClicked = {
                    onboardingProfileViewModel.handleEvent(OnboardingScreensStatesMainContract.Event.FinishFillingOutTheProfileClicked)
                },
                onTurnBackClicked = { navController.navigate(Destinations.FILLING_OUT_THE_USER_PROFILE3.route) })
            }
        }
    }

enum class Destinations(val route: String) {
    LOGIN("login"),
    RESET1("reset1"),
    RESET2("reset2"),
    RESET3("reset3"),
    REGISTRATION1("registration1"),
    REGISTRATION2("registration2"),
    PUBLIC_PROFILE("publicProfile"),
    ALL_REVIEWS("allReviews"),
    ALL_PLANNED_EVENTS("allPlannedEvents"),
    FILLING_OUT_THE_USER_PROFILE_START("fillingOutTheUserProfileStart"),
    FILLING_OUT_THE_USER_PROFILE1("fillingOutTheUserProfile1"),
    FILLING_OUT_THE_USER_PROFILE2("fillingOutTheUserProfile2"),
    FILLING_OUT_THE_USER_PROFILE3("fillingOutTheUserProfile3"),
    FILLING_OUT_THE_USER_PROFILE4("fillingOutTheUserProfile4"),
}