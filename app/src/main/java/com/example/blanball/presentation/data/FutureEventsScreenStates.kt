package com.example.blanball.presentation.data

class FutureEventsMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,

        ) : UiState

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {

    }
}