package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class OnboardingScreensStatesMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val dayDropDownState: MutableState<String> = mutableStateOf(""),
        val monthDropDownState: MutableState<String> = mutableStateOf(""),
        val yearDropDownState: MutableState<String> = mutableStateOf(""),
        val selectDocumentState: MutableState<String> = mutableStateOf(""),
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