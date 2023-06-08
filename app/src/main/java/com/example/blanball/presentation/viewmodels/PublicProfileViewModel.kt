package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.usecases.interfaces.GetUserPlannedEventsByIdUseCase
import com.example.domain.usecases.interfaces.GetUserProfileByIdUseCase
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import com.example.domain.utils.Integers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PublicProfileViewModel @Inject constructor(
    internal val getUserProfileByIdUseCase: GetUserProfileByIdUseCase,
    internal val getUserReviewsByIdUseCase: GetUserReviewsByIdUseCase,
    internal val getUserPlannedEventsByIdUseCase: GetUserPlannedEventsByIdUseCase,
) : ViewModel() {

    private var job: Job? = null

    private var reviewsPage = Integers.ONE
    private var eventsPage = Integers.ONE

    private val defaultState
        get() = PublicProfileMainContract.State(
            state = PublicProfileMainContract.ScreenViewState.Loading
        )

    private val currentState: PublicProfileMainContract.State
        get() = uiState.value as PublicProfileMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<PublicProfileMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<PublicProfileMainContract.Effect> = _sideEffect.asSharedFlow()

    init {
        handleScreenState(currentState.state)
    }

    private fun handleScreenState(screenViewState: PublicProfileMainContract.ScreenViewState) {
        when (screenViewState) {
            is PublicProfileMainContract.ScreenViewState.Loading -> {
                getUserPublicProfileById()
                getUserReviewsById(Integers.ONE)
                getUserPlannedEventsById(Integers.ONE)
            }

            is PublicProfileMainContract.ScreenViewState.LoadingError -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    _sideEffect.emit(PublicProfileMainContract.Effect.ShowToast("Error"))
                }
            }
        }
    }

    private fun getUserPublicProfileById() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getUserProfileByIdUseCase.executeGetUserProfileById().let {
                when (it) {
                    is GetUserProfileByIdResultEntity.Success ->
                        setState {
                            copy(
                                userFirstNameText = mutableStateOf(it.data.profile.name),
                                userLastNameText = mutableStateOf(it.data.profile.last_name),
                                userAvatar = mutableStateOf(it.data.profile.avatar_url),
                                userRoleText = mutableStateOf(it.data.role),
                                userIsVerified = mutableStateOf(it.data.is_verified),
                                userEmail = mutableStateOf(it.data.email),
                                userPhoneNumberText = mutableStateOf(it.data.phone),
                                userHeightText = mutableStateOf(it.data.profile.height),
                                userWeightText = mutableStateOf(it.data.profile.weight),
                                aboutUserText = mutableStateOf(it.data.profile.about_me),
                                userPositionText = mutableStateOf(it.data.profile.position),
                                userWorkingLegText = mutableStateOf(it.data.profile.working_leg),
                                rating = mutableStateOf(it.data.raiting),
                                state = PublicProfileMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }

                    is GetUserProfileByIdResultEntity.Error -> {
                        setState {
                            copy(state = PublicProfileMainContract.ScreenViewState.LoadingError)
                        }
                    }
                }
            }
        }
    }

    private fun getUserReviewsById(page: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUserReviewsByIdUseCase.executeGetUserReviewsById(page)) {
                is GetUserReviewsByIdResultEntity.Success -> {
                    val reviews = result.data.results
                    reviews?.let {
                        setState {
                            copy(
                                reviewsCount = mutableStateOf(result.data.total_count),
                                reviewsList = mutableStateOf(currentState.reviewsList.value + it),
                                isLoadingMoreReviews = false
                            )
                        }
                    }
                    val nextPage = result.data.next
                    if (nextPage.isNullOrEmpty()) {
                        setState {
                            copy(allReviewsLoaded = true)
                        }
                    }
                }

                is GetUserReviewsByIdResultEntity.Error -> {
                    setState {
                        copy(
                            state = PublicProfileMainContract.ScreenViewState.LoadingError,
                            isLoadingMoreReviews = false
                        )
                    }
                }
            }
        }
    }

    private fun getUserPlannedEventsById(page: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (val result =
                getUserPlannedEventsByIdUseCase.executeGetUserPlannedEventsById(page)) {
                is GetUserPlannedEventsByIdResultEntity.Success -> {
                    val events = result.data.results
                    events?.let {
                        setState {
                            copy(
                                plannedEventsCount = mutableStateOf(result.data.total_count),
                                plannedEventsList = mutableStateOf(currentState.plannedEventsList.value + it),
                                isLoadingMoreEvents = false,
                            )
                        }
                    }
                    val nextPage = result.data.next
                    if (nextPage.isNullOrEmpty()) {
                        setState { copy(allEventsLoaded = true) }
                    }
                }

                is GetUserPlannedEventsByIdResultEntity.Error -> {
                    setState {
                        copy(
                            state = PublicProfileMainContract.ScreenViewState.LoadingError,
                            isLoadingMoreEvents = false
                        )
                    }
                }
            }
        }
    }

    fun loadMoreReviews() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreReviews || currentState.allReviewsLoaded)) {
                setState {
                    copy(isLoadingMoreReviews = true)
                }
                reviewsPage++
                getUserReviewsById(reviewsPage)
            }
        }
    }

    fun loadMoreEvents() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreEvents || currentState.allEventsLoaded)) {
                setState {
                    copy(isLoadingMoreEvents = true)
                }
                eventsPage++
                getUserPlannedEventsById(eventsPage)
            }
        }
    }

    private fun setState(reduce: PublicProfileMainContract.State.() -> PublicProfileMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}