package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

interface UiState

interface UiEvent

interface UiEffect

class MainContract {

    sealed class Event: UiEvent {
        object SendCodeClicked: Event()
        object SendEmailResetRequest: Event()
        object CancelClicked: Event()
    }

    data class State(
        val state: ScreenViewState,
        val data: UiData? = null,
        var emailText: MutableState<String> = mutableStateOf("") ,
        var codeText: List<MutableState<String>> = List(5){ mutableStateOf("") },
        val bottomTabsVisible: Boolean = true,
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessRes
                    .fillMaxSize()etRequest: ScreenViewState()
    }

    sealed class Effect: UiEffect {
       class ShowToast(val error: String): Effect()
    }

    data class UiData(
        val progress: Float
    )
}




