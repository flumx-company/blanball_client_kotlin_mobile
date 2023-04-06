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
        var loginText: String = "",
        var passwordText: String = "",
        val bottomTabsVisible: Boolean = true,
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessLogin: ScreenViewState()
    }

    sealed class Effect: UiEffect {

    }

    data class UiData(
        val progress: Float
    )
}




