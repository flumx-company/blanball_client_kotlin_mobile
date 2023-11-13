package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.convertToPositionCode
import com.example.blanball.utils.workers.LoadUsersWorker
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    internal val loadUsersWorker: LoadUsersWorker,
    private val application: Application,
) : ViewModel() {
    private val defaultState
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
                        allUsersLoaded = false,
                    )
                }
                loadUsersWorker.resetPage()
                loadUsersWorker.loadUsersList(
                    viewModel = this@UsersRatingViewModel,
                    getUsersListUseCase = getUsersListUseCase,
                    currentState = currentState,
                    gender = currentState.genderSelectionState.value.stringValue ,
                    ageMin = currentState.ageSliderPosition.value.start.toInt(),
                    ageMax = currentState.ageSliderPosition.value.endInclusive.toInt(),
                    ordering = currentState.usersOrderingSelectionState.value.stringValue,
                    position = currentState.positionSelectedItem.value.convertToPositionCode(application.applicationContext)
                )
            }
            is RatingUsersMainContract.ScreenViewState.LoadingError -> {
            }
            else -> {}
        }
    }

  fun loadMoreUsers() {
      viewModelScope.launch(Dispatchers.IO) {
          loadUsersWorker.loadMoreUsers(
              viewModel = this@UsersRatingViewModel,
              getUsersListUseCase = getUsersListUseCase,
              currentState = currentState,
              gender = currentState.genderSelectionState.value.stringValue ,
              ageMin = currentState.ageSliderPosition.value.start.toInt(),
              ageMax = currentState.ageSliderPosition.value.endInclusive.toInt(),
              ordering = currentState.usersOrderingSelectionState.value.stringValue,
              position = currentState.positionSelectedItem.value.convertToPositionCode(application.applicationContext)
          )
      }
  }

    fun setState(reduce: RatingUsersMainContract.State.() -> RatingUsersMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    override fun onCleared() {
        super.onCleared()
        loadUsersWorker.cancelJob()
    }
}