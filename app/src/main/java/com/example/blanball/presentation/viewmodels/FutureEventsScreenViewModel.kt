package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.usecases.interfaces.GetAllEventsUseCase
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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FutureEventsScreenViewModel
@Inject constructor(
    internal val getAllEventsUseCase: GetAllEventsUseCase,
    private val application: Application,
) : ViewModel() {
    private var job: Job? = null
    private var page = Integers.ONE

    val defaultState
        get() = FutureEventsMainContract.State(
            state = FutureEventsMainContract.ScreenViewState.Idle,
        )

    val currentState: FutureEventsMainContract.State
        get() = uiState.value as FutureEventsMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<FutureEventsMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<FutureEventsMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleScreenState(screenViewState: FutureEventsMainContract.ScreenViewState) {
        when (screenViewState) {
            is FutureEventsMainContract.ScreenViewState.Loading -> {
                setState {
                    copy(
                        allEventsList = mutableStateOf(emptyList()),
                        isAllEventsLoaded = false,
                    )
                }
                page = Integers.ONE
                loadAllEventsList()
            }
            is FutureEventsMainContract.ScreenViewState.LoadingError -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    _sideEffect.emit(FutureEventsMainContract.Effect.ShowToast("Error"))
                }
            }
            else -> {}
        }
    }

    private fun loadAllEventsList() {
        job = viewModelScope.launch(Dispatchers.IO) {
            val pageToLoad = page
            val gender = currentState.genderSelectionState.value.stringValue
            val ageMin = currentState.ageSliderPosition.value.start.toInt()
            val ageMax = currentState.ageSliderPosition.value.endInclusive.toInt()
            val ordering = currentState.usersOrderingSelectionState.value.stringValue
            val position = currentState.positionSelectedItem.value.convertToPositionCode(application.applicationContext)

            when (val result = getUsersListUseCase.executeGetUsersList(
                page = pageToLoad,
                gender = gender,
                age_min = ageMin,
                age_max = ageMax,
                ordering = ordering,
                position = position,
            )) {
                is GetUsersListResultEntity.Success -> {
                    val users = result.data.results
                    users?.let {
                        setState {
                            copy(
                                usersList = mutableStateOf(currentState.usersList.value + it),
                                isLoadingMoreUsers = false,
                                userCounter = mutableStateOf(result.data.total_count),
                                state = RatingUsersMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }
                    }
                    val nextPage = result.data.next
                    if (nextPage.isNullOrEmpty()) {
                        setState {
                            copy(allUsersLoaded = true)
                        }
                    }
                }
                is GetUsersListResultEntity.Error -> {
                    setState {
                        copy(
                            state = RatingUsersMainContract.ScreenViewState.LoadingError,
                        )
                    }
                }
            }
        }
    }

    fun loadMoreAllEvents() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreAllEvents || currentState.isAllEventsLoaded)) {
                setState {
                    copy(isLoadingMoreAllEvents = true)
                }
                page++
                loadAllEventsList()
            }
        }
    }

    private fun setState(reduce: FutureEventsMainContract.State.() -> FutureEventsMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}