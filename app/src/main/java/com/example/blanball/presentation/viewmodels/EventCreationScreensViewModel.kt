package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.EventCreationScreenMainContract
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
class EventCreationScreensViewModel
@Inject constructor(
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = EventCreationScreenMainContract.State(
            state = EventCreationScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: EventCreationScreenMainContract.State
        get() = uiState.value as EventCreationScreenMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<EventCreationScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<EventCreationScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    private fun setState(reduce: EventCreationScreenMainContract.State.() -> EventCreationScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}