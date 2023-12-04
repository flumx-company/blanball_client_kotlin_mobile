package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class EmailVerificationMainContract {

    sealed class Event : UiEvent {
        object SendCodeToUserEmailClicked : Event()
        object VerifyEmailClicked : Event ()
    }

    data class State(
        val state: ScreenViewState,
        val codeText: List<MutableState<String>> = List(5) { mutableStateOf("") },
        val userEmailText: MutableState<String>  = mutableStateOf(""),
        val isSendCodeToUserEmailError: MutableState<Boolean> = mutableStateOf(false),
        val isSendCodeToUserEmailSuccess: MutableState<Boolean> = mutableStateOf(false),
        val isEmailVerifySuccess: MutableState<Boolean> = mutableStateOf(false),
        val isEmailVerifyError: MutableState<Boolean> = mutableStateOf(false),
        val isEmailVerified: MutableState<Boolean> = mutableStateOf(false),
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
        object SendCodeToUserEmailSuccess : ScreenViewState()
        object SendCodeToUserEmailError : ScreenViewState()
        object EmailVerifySuccess : ScreenViewState()
        object EmailVerifyError : ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }
}