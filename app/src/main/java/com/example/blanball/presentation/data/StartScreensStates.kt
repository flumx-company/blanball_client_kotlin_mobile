package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

interface UiState

interface UiEvent

interface UiEffect

class MainContract {

    sealed class Event: UiEvent {
        object SendCodeClicked: Event()
        object SendEmailResetRequestClicked: Event()
        object CompleteResetClicked: Event()
        object RegistrationClicked: Event()
    }

    data class State(
        val state: ScreenViewState,
        val data: UiData? = null,
        var resetEmailText: MutableState<String> = mutableStateOf(""),
        var codeText: List<MutableState<String>> = List(5){ mutableStateOf("") },
        var newPassText: MutableState<String> = mutableStateOf(""),
        var nameText: MutableState<String> = mutableStateOf(""),
        var phoneNumberText: MutableState<String> = mutableStateOf(""),
        var repeatNewPassText: MutableState<String> = mutableStateOf(""),
        var passwordVisibility: MutableState<Boolean> = mutableStateOf(false),
        var successValidEmail: MutableState<Boolean> = mutableStateOf(false),
        var genderIsMale: MutableState<Boolean> = mutableStateOf(false),
        var genderIsFemale: MutableState<Boolean> = mutableStateOf(false),
        var registerEmailText: MutableState<String> = mutableStateOf(""),
        var resetPassText: MutableState<String> = mutableStateOf(""),
        var resetPassTextRemember: MutableState<String> = mutableStateOf(""),
        var lostInSystemSwitchButton: MutableState<Boolean> = mutableStateOf(false),
        var privacyPolicyCheckbox: MutableState<Boolean> = mutableStateOf(false),
    ) : UiState

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle: ScreenViewState()
        object SuccessResetRequest: ScreenViewState()
        object SuccessSendCodeRequest: ScreenViewState()
        object SuccessCompleteResetRequest: ScreenViewState()
    }

    sealed class Effect: UiEffect {
       class ShowToast(val message: String): Effect()
    }

    data class UiData(
        val progress: Float
    )
}




