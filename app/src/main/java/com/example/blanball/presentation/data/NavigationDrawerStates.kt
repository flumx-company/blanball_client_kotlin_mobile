package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class NavigationDrawerMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        var userFirstNameText: MutableState<String> = mutableStateOf(""),
        var userLastNameText: MutableState<String> = mutableStateOf(""),
        val userAvatar: MutableState<String?> = mutableStateOf(null),
    ) : UiState


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

}