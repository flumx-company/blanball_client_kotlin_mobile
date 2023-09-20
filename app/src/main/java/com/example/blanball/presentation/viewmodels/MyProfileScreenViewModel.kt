package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.MyProfileScreensMainContract
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
        get() = MyProfileScreensMainContract.State(
            state = MyProfileScreensMainContract.ScreenViewState.Idle,
        )

    val currentState: MyProfileScreensMainContract.State
        get() = uiState.value as MyProfileScreensMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<MyProfileScreensMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MyProfileScreensMainContract.Effect> = _sideEffect.asSharedFlow()

    private fun setState(reduce: MyProfileScreensMainContract.State.() -> MyProfileScreensMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}