package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class FoundAnErrorScreenMainContract {

    sealed class Event : UiEvent {
       object SendFeedbackButtonClicked: Event()
       object CloseButtonClicked: Event()
    }  //TODO()

    data class State(
        val state: ScreenViewState,
        var errorTopicText: MutableState<String> = mutableStateOf(""),
        var errorDescriptionText: MutableState<String> = mutableStateOf(""),
    ) : UiState

    sealed class Effect: UiEffect {
        class ShowToast(val message: String): Effect()
    }

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
    }
}