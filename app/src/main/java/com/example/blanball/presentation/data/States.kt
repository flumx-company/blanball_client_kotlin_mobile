package com.example.blanball.presentation.data

interface UiState

interface UiEvent

interface UiEffect

class MainContract {

    // Events that user performed
    sealed class Event : UiEvent {
        object LoginClicked: Event()
        object RememberPasswordClicked: Event()
    }

    // Ui View States
    data class State(
        val state: ScreenViewState,
        val data: UiData? = null,
        var loginText: String = "",
        var passwordText: String = "",
        val bottomTabsVisible: Boolean = true,
    ) : UiState



    // View State that related to Random Number
    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessLogin: ScreenViewState()
    }

    // Side effects
    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }
}


data class UiData(
    val progress: Float
)
