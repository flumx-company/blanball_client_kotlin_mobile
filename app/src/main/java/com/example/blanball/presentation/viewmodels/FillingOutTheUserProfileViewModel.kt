package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.usecases.interfaces.FillingTheUserProfileUseCase
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
class OnboardingProfileViewModel @Inject constructor(
    internal val fillingTheUserProfileUseCase: FillingTheUserProfileUseCase
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = OnboardingScreensStatesMainContract.State(
            state = OnboardingScreensStatesMainContract.ScreenViewState.Idle
        )

    val currentState: OnboardingScreensStatesMainContract.State
        get() = uiState.value as OnboardingScreensStatesMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<OnboardingScreensStatesMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<OnboardingScreensStatesMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
          is OnboardingScreensStatesMainContract.Event.FinishFillingOutTheProfileClicked -> {
              setState {
                  copy(
                      state = OnboardingScreensStatesMainContract.ScreenViewState.Loading
                  )
              }
               requestToMakeChangesToTheProfile()
          }
        }
    }

    private fun requestToMakeChangesToTheProfile(){
        job = viewModelScope.launch(Dispatchers.IO) {
            fillingTheUserProfileUseCase.executeUpdateUserProfile(
                birthday = "${currentState.yearBirthdayState.value}-${currentState.monthBirthdayState.value}-${currentState.dayBirthdayState.value}",
                height = currentState.heightState.value.toInt(),
                weight = currentState.weightState.value.toInt(),
                position = currentState.positionState.value,
                working_leg = currentState.workingLegState.value,
                place_name = "string",
            )
        }
    }


     fun setState(reduce: OnboardingScreensStatesMainContract.State.() -> OnboardingScreensStatesMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}