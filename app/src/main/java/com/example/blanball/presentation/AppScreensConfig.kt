package com.example.blanball.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.PublicProfileViewModel
import com.example.blanball.presentation.viewmodels.RegistrationViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordViewModel
import com.example.blanball.presentation.viewmodels.UsersRatingViewModel
import com.example.blanball.presentation.views.screens.login.LoginScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllPlannedEventsScreen
import com.example.blanball.presentation.views.screens.publicprofile.AllReviewsScreen
import com.example.blanball.presentation.views.screens.publicprofile.PublicProfileScreen
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep1
import com.example.blanball.presentation.views.screens.registration.RegistrationScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep1
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep2
import com.example.blanball.presentation.views.screens.resset.ResetPasswordScreenStep3
import com.example.blanball.presentation.views.screens.usersrating.UsersRatingScreen

@Composable
fun AppScreensConfig(
    navController: NavHostController,
    resetPassViewModel: ResetPasswordViewModel,
    registrationViewModel: RegistrationViewModel,
    publicProfileViewModel: PublicProfileViewModel,
    loginViewModel: LoginViewModel,
    usersRatingViewModel: UsersRatingViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.USERS_RATING.route)
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
                onRegistrationClicked = { registrationViewModel.handleEvent(StartScreensMainContract.Event.RegistrationClicked) },
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
                publicProfileViewModel.loadUserProfileData()
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
            AllReviewsScreen(state = state,
                onLoadMoreReviews = { publicProfileViewModel.loadMoreReviews() })
        }

        composable(Destinations.ALL_PLANNED_EVENTS.route) {
            val state = publicProfileViewModel.uiState.collectAsState().value
            AllPlannedEventsScreen(state = state, onLoadMoreEvents = {
                publicProfileViewModel.loadMoreEvents()
            })
        }

        composable(Destinations.USERS_RATING.route) {
            val state = usersRatingViewModel.uiState.collectAsState().value
            val currentState = usersRatingViewModel.currentState


            LaunchedEffect(currentState.state) {

                    if (currentState.state is RatingUsersMainContract.ScreenViewState.Loading ||  currentState.state is RatingUsersMainContract.ScreenViewState.LoadingWithFilters || currentState.state is RatingUsersMainContract.ScreenViewState.LoadingWithNewOrdering) {
                        usersRatingViewModel.handleScreenState(currentState.state)
                    }
            }

            UsersRatingScreen(
                state = state,
                onLoadMoreUsers = {
                    usersRatingViewModel.loadMoreUsers()
                },
                onClickedToLoadWithNewFilters = {
                    usersRatingViewModel.setState {
                        copy(
                            openFiltersDialog = mutableStateOf(false),
                            state = RatingUsersMainContract.ScreenViewState.LoadingWithFilters,
                        )
                    }
                },
                onClickedToChangeOrdering = {usersRatingViewModel.setState {
                    copy(
                        orderingIconState = mutableStateOf(!orderingIconState.value),
                        usersOrderingSelectionState = mutableStateOf(RatingUsersMainContract.UserOrderingSelectionState.FIRST_OLDER),
                        state = RatingUsersMainContract.ScreenViewState.LoadingWithNewOrdering
                    )
                }
                }
            )
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
    USERS_RATING("usersRating")
}