package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.MyProfileScreenMainContract
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
class MyProfileScreenViewModel
@Inject constructor(
) : ViewModel() {
    private var job: Job? = null

    val defaultState
        get() = MyProfileScreenMainContract.State(
            state = MyProfileScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: MyProfileScreenMainContract.State
        get() = uiState.value as MyProfileScreenMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<MyProfileScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MyProfileScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    private fun setState(reduce: MyProfileScreenMainContract.State.() -> MyProfileScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}