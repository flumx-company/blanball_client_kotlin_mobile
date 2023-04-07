package com.example.blanball.presentation.data

interface UiState

interface UiEvent

interface UiEffect

class MainContract {

    sealed class Event: UiEvent {
        object SendCodeClicked: Event()
        object CancelClicked: Event()
    }

    data class State(
        val state: ScreenViewState,
        val data: UiData? = null,
        var emailText: String = "",
        val bottomTabsVisible: Boolean = true,
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessSendCode: ScreenViewState()
    }

    sealed class Effect: UiEffect {
       class ShowToast(val error: String): Effect()
    }

    data class UiData(
        val progress: Float
    )
}




