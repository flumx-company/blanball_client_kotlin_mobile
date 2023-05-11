package com.example.blanball.presentation.data

class PublicProfileMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: PublicProfileMainContract.ScreenViewState,
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }

    data class UiData(
        val progress: Float
    )
}