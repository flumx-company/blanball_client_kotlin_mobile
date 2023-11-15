package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class EventScreenMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val currentEventId: MutableState<Int> = mutableStateOf(0),
        val eventName: MutableState<String> = mutableStateOf(""),
        val sportType: MutableState<String> = mutableStateOf(""),
        val eventDateAndTime: MutableState<String> = mutableStateOf(""),
        val eventPlaceName: MutableState<String> = mutableStateOf(""),
        val eventDescription: MutableState<String> = mutableStateOf(""),
        val eventGenders: MutableState<String> = mutableStateOf(""),
        val eventAuthorName: MutableState<String> = mutableStateOf(""),
        val eventAuthorPhone: MutableState<String> = mutableStateOf(""),
        val eventAuthorAvatar: MutableState<String> = mutableStateOf(""),

        ) : UiState

    sealed class ScreenViewState {
        object Idle : ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess : ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {
    }
}