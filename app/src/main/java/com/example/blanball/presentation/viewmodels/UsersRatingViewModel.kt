package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.convertToPositionCode
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
    private val application: Application
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

       fun handleScreenState(screenViewState: RatingUsersMainContract.ScreenViewState) {
        when (screenViewState) {
            is RatingUsersMainContract.ScreenViewState.Loading -> {
                setState {
                    copy(
                    usersList = mutableStateOf(emptyList()),
                    )
                }
                getUsersList(
                    page = page,
                    gender = currentState.genderSelectionState.value.stringValue,
                    age_min = currentState.ageSliderPosition.value.start.toInt(),
                    age_max = currentState.ageSliderPosition.value.endInclusive.toInt(),
                    ordering = currentState.usersOrderingSelectionState.value.stringValue,
                    position = currentState.positionSelectedItem.value.convertToPositionCode(application.applicationContext),
                )
            }
            is RatingUsersMainContract.ScreenViewState.LoadingError -> {
                job = viewModelScope.launch(Dispatchers.IO) {
                    _sideEffect.emit(RatingUsersMainContract.Effect.ShowToast("Error"))
                }
            }
        }
    }

    private fun getUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?,
    ) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUsersListUseCase.executeGetUsersList(
                page = page,
                gender = gender,
                age_min = age_min,
                age_max = age_max,
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

   fun loadMoreUsers() {
       job = viewModelScope.launch(Dispatchers.IO) {
           if (!(currentState.isLoadingMoreUsers || currentState.allUsersLoaded)) {
               setState {
                   copy(isLoadingMoreUsers = true)
               }
               page++
               getUsersList(
                   page = page,
                   gender = currentState.genderSelectionState.value.stringValue,
                   age_min = currentState.ageSliderPosition.value.start.toInt(),
                   age_max = currentState.ageSliderPosition.value.endInclusive.toInt(),
                   ordering = currentState.usersOrderingSelectionState.value.stringValue,
                   position = currentState.GamePositionSelectionState.value.stringValue?.convertToPositionCode(application.applicationContext),
               )
           }
       }
   }

    fun setState(reduce: RatingUsersMainContract.State.() -> RatingUsersMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}