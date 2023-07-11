package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class StartScreensMainContract {

    sealed class Event: UiEvent {
        object SendCodeClicked: Event()
        object SendEmailResetRequestClicked: Event()
        object CompleteResetClicked: Event()
        object RegistrationClicked: Event()
        object LoginClicked: Event()
    }

    data class State(
        val state: ScreenViewState,
        val data: UiData? = null,
        var resetEmailText: MutableState<String> = mutableStateOf(""),
        var codeText: List<MutableState<String>> = List(5){ mutableStateOf("") },
        var newPassText: MutableState<String> = mutableStateOf(""),
        var firstNameText: MutableState<String> = mutableStateOf(""),
        var lastNameText: MutableState<String> = mutableStateOf(""),
        var phoneNumberText: MutableState<String> = mutableStateOf(""),
        var repeatNewPassText: MutableState<String> = mutableStateOf(""),
        var passwordRegistrationVisibility: MutableState<Boolean> = mutableStateOf(false),
        var passwordResetVisibility: MutableState<Boolean> = mutableStateOf(false),
        var repeatPasswordRegistrationVisibility: MutableState<Boolean> = mutableStateOf(false),
        var repeatPasswordResetVisibility: MutableState<Boolean> = mutableStateOf(false),
        var loginPasswordVisibility: MutableState<Boolean> = mutableStateOf(false),
        var successValidEmail: MutableState<Boolean> = mutableStateOf(false),
        var genderIsMale: MutableState<Boolean> = mutableStateOf(false),
        var genderIsFemale: MutableState<Boolean> = mutableStateOf(false),
        var registrationEmailText: MutableState<String> = mutableStateOf(""),
        var registrationPassText: MutableState<String> = mutableStateOf(""),
        var registrationPassTextRemember: MutableState<String> = mutableStateOf(""),
        var lostInSystemSwitchButton: MutableState<Boolean> = mutableStateOf(false),
        var privacyPolicyCheckbox: MutableState<Boolean> = mutableStateOf(false),
        val loginEmailText: MutableState<String> = mutableStateOf(""),
        val loginPasswordText: MutableState<String> = mutableStateOf(""),
        val rememberMeCheckbox: MutableState<Boolean> = mutableStateOf(false),
        val isErrorLoginRequest: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessLoginRequest:  MutableState<Boolean> = mutableStateOf(false),
        val isErrorResetEmailState: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessResetRequest: MutableState<Boolean> = mutableStateOf(false),
        val isErrorSendCodeState: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessSendCodeState: MutableState<Boolean> = mutableStateOf(false),
        val isErrorCompleteResetState: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessCompleteResetState: MutableState<Boolean> = mutableStateOf(false),
        val isErrorRegistrationNewPass: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessRegistrationNewPass: MutableState<Boolean> = mutableStateOf(false),
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessResetRequest: ScreenViewState()
        object ErrorResetRequest: ScreenViewState()
        object SuccessSendCodeRequest: ScreenViewState()
        object ErrorSendCodeRequest: ScreenViewState()
        object SuccessCompleteResetRequest: ScreenViewState()
        object ErrorCompleteResetRequest: ScreenViewState()
        object SuccessLogin: ScreenViewState()
        object LoginError: ScreenViewState()
        object SuccessRegistration: ScreenViewState()
        object ErrorRegistration: ScreenViewState()
    }

    sealed class Effect: UiEffect {
       class ShowToast(val message: String): Effect()
    }

    data class UiData(
        val progress: Float
    )
}