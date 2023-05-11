package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.blanball.presentation.data.PublicProfileMainContract
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
class PublicProfileViewModel @Inject constructor() : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = PublicProfileMainContract.State(
            state = PublicProfileMainContract.ScreenViewState.Idle
        )

    val currentState: PublicProfileMainContract.State
        get() = uiState.value as PublicProfileMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<PublicProfileMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<PublicProfileMainContract.Effect> = _sideEffect.asSharedFlow()

    //TODO()
//    fun handleEvent(event: UiEvent) {
//        when (event) {
//            is PublicProfileMainContract.Event. -> {
//                setState {
//                    copy(
//                        state = PublicProfileMainContract.ScreenViewState.Loading,
//                    )
//                }
//
//            }
//            is PublicProfileMainContract.Event. -> {
//                setState {
//                    copy(
//                        state = PublicProfileMainContract.ScreenViewState.Loading
//                    )
//                }
//
//            }
//            is PublicProfileMainContract.Event. -> {
//                setState {
//                    copy(
//                        state = PublicProfileMainContract.ScreenViewState.Loading
//                    )
//                }
//
//            }
//        }
//    }


    private fun setState(reduce: PublicProfileMainContract.State.() -> PublicProfileMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}
