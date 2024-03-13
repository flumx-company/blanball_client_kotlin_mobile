package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.FoundAnErrorScreenMainContract
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
class FoundAnErrorViewModel
@Inject constructor(
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = FoundAnErrorScreenMainContract.State(
            state = FoundAnErrorScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: FoundAnErrorScreenMainContract.State
        get() = uiState.value as FoundAnErrorScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<FoundAnErrorScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<FoundAnErrorScreenMainContract.Effect> = _sideEffect.asSharedFlow()

//    fun handleEvent(event: UiEvent) {
//    } TODO()


    private fun setState(reduce: FoundAnErrorScreenMainContract.State.() -> FoundAnErrorScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}