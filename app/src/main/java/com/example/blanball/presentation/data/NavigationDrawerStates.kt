package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class NavigationDrawerMainContract
{
    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val userFirstNameText: MutableState<String> = mutableStateOf(""),
        val userLastNameText: MutableState<String> = mutableStateOf(""),
        val userAvatar: MutableState<String?> = mutableStateOf(""),
        val isDrawerOpen: MutableState<Boolean> = mutableStateOf(false),
        val isSplashScreenActivated: MutableState<Boolean> = mutableStateOf(false),
    ) : UiState


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

}