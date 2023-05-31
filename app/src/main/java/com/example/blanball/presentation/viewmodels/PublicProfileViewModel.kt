package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.responses.GetUserPlannedEventsByIdResultResponseEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseResultEntity
import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.usecases.interfaces.GetUserPlannedEventsByIdUseCase
import com.example.domain.usecases.interfaces.GetUserProfileByIdUseCase
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
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

    val defaultState
        get() = PublicProfileMainContract.State(
            state = PublicProfileMainContract.ScreenViewState.Loading
        )

    val currentState: PublicProfileMainContract.State
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

    private fun handleScreenState (screenViewState: PublicProfileMainContract.ScreenViewState) {
        when (screenViewState) {
            is PublicProfileMainContract.ScreenViewState.Loading -> {
                getUserReviewsById()
                getUserPublicProfileById()
                getUserPlannedEventsById()
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

    private fun getUserReviewsById() {
        job = viewModelScope.launch(Dispatchers.IO) {
            var page = 1
            val allReviews = mutableStateListOf<GetUserReviewsByIdResponseResultEntity>()
            while (true) {
                when (val result = getUserReviewsByIdUseCase.executeGetUserReviewsById(page)) {
                    is GetUserReviewsByIdResultEntity.Success -> {
                        setState { copy(reviewsCount = mutableStateOf(result.data.total_count))}
                        val reviews = result.data.results
                        val nextPage = result.data.next
                        if (nextPage.isNullOrEmpty()) {
                            reviews?.let { allReviews.addAll(it) }
                            setState { copy(reviewsList = mutableStateOf(allReviews)) }
                            break
                        } else {
                            reviews?.let { allReviews.addAll(it) }
                            page++
                        }
                    }

                    is GetUserReviewsByIdResultEntity.Error -> {
                        setState {
                            copy(state = PublicProfileMainContract.ScreenViewState.LoadingError)
                        }
                        break
                    }
                }
            }
        }
    }

    private fun getUserPlannedEventsById() {
        job = viewModelScope.launch(Dispatchers.IO) {
            var page = 1
            val allPlannedEvents =
                mutableStateListOf<GetUserPlannedEventsByIdResultResponseEntity>()
            while (true) {
                when (val result = getUserPlannedEventsByIdUseCase.executeGetUserPlannedEventsById(page)) {
                    is GetUserPlannedEventsByIdResultEntity.Success -> {
                        setState { copy(plannedEventsCount = mutableStateOf(result.data.total_count))}
                        val plannedEvents = result.data.results
                        val nextPage = result.data.next
                        if (nextPage.isNullOrEmpty()) {
                            plannedEvents?.let { allPlannedEvents.addAll(it) }
                            setState { copy(plannedEventsList = mutableStateOf(allPlannedEvents)) }
                            break
                        }
                        else {
                            plannedEvents?.let { allPlannedEvents.addAll(it) }
                            page++
                        }
                    }

                    is GetUserPlannedEventsByIdResultEntity.Error -> {
                        setState {
                            copy(state = PublicProfileMainContract.ScreenViewState.LoadingError)
                        }
                        break
                    }
                }
            }
        }
    }

    private fun setState(reduce: PublicProfileMainContract.State.() -> PublicProfileMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}
