package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingProfileViewModel @Inject constructor() : ViewModel() {

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

        }
    }
    

     fun setState(reduce: OnboardingScreensStatesMainContract.State.() -> OnboardingScreensStatesMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}