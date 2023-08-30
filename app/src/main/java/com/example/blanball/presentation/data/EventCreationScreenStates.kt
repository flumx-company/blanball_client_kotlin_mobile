package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class EventCreationScreenMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val eventName: MutableState<String> = mutableStateOf(""),
        val typeOfEvent: MutableState<String> = mutableStateOf(""),
        val playersGenderStates: MutableState<PlayersGenderStates> = mutableStateOf(EventCreationScreenMainContract.PlayersGenderStates.NO_SELECT),
        val timeAndDateOfEvent: MutableState<String> = mutableStateOf(""),
        val placeOfEvent: MutableState<String> = mutableStateOf(""),
        val sportType: MutableState<String> = mutableStateOf("")
        ) : UiState

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {

    }

    enum class PlayersGenderStates() {
        NO_SELECT,
        ALL,
        MANS,
        WOMANS,
    }
}