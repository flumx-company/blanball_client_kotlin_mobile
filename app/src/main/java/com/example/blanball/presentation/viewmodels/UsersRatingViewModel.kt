package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.usecases.interfaces.GetUsersListUseCase
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
class UsersRatingViewModel @Inject constructor(
    internal val getUsersListUseCase: GetUsersListUseCase,
) : ViewModel() {

    private var job: Job? = null
    private var page = Integers.ONE

    val defaultState
        get() = RatingUsersMainContract.State(
            state = RatingUsersMainContract.ScreenViewState.Loading
        )

    val currentState: RatingUsersMainContract.State
        get() = uiState.value as RatingUsersMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<RatingUsersMainContract.Effect> =
        MutableSharedFlow(replay = 0)

    val sideEffect: SharedFlow<RatingUsersMainContract.Effect> = _sideEffect.asSharedFlow()

    init {
        handleScreenState(currentState.state)
    }

    fun handleScreenState(screenViewState: RatingUsersMainContract.ScreenViewState) {
        when (screenViewState) {
            is RatingUsersMainContract.ScreenViewState.Loading -> {
                getUsersList(Integers.ONE)
            }

            is RatingUsersMainContract.ScreenViewState.LoadingWithFilters -> {
                setState {
                    copy(
                        usersList = mutableStateOf(emptyList()),
                    )
                }
                getUsersList(
                    page = Integers.ONE,
                    age_min = currentState.ageSliderPosition.value.start.toInt(),
                    age_max = currentState.ageSliderPosition.value.endInclusive.toInt(),
                )
            }

            is RatingUsersMainContract.ScreenViewState.LoadingError -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    _sideEffect.emit(RatingUsersMainContract.Effect.ShowToast("Error"))
                }
            }
        }
    }

    fun getUsersList(
        page: Int,
        gender: String? = null,
        age_min: Int? = null,
        age_max: Int? = null,
        ordering: String? = null
    ) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUsersListUseCase.executeGetUsersList(
                page,
                gender = gender,
                age_min = age_min,
                age_max = age_max,
                ordering = ordering
            )) {
                is GetUsersListResultEntity.Success -> {
                    val users = result.data.results
                    users?.let {
                        setState {
                            copy(
                                usersList = mutableStateOf(currentState.usersList.value + it),
                                isLoadingMoreUsers = false,
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
                            isLoadingMoreUsers = false,
                        )
                    }
                }
            }

        }
    }

    fun loadMoreUsers() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreUsers || currentState.allUsersLoaded))
                setState {
                    copy(isLoadingMoreUsers = true)
                }
            page++
            getUsersList(page)
        }
    }

    fun setState(reduce: RatingUsersMainContract.State.() -> RatingUsersMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}
