package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class EmailVerificationMainContract {

    sealed class Event: UiEvent {

    }

    data class State(
        val state: ScreenViewState,
        val codeText:List<MutableState<String>> = List(5){ mutableStateOf("") },
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object EmailVerifySuccess: ScreenViewState()
    }

    sealed class Effect: UiEffect {
        class ShowToast(val message: String): Effect()
    }
}