package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class MyProfileScreensMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val myAvatarUrl: MutableState<String> = mutableStateOf(""),
        val myFirstNameText: MutableState<String> = mutableStateOf("Женя"),
        val myLastNameText: MutableState<String> = mutableStateOf("Жук"),
        val phoneNumberRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val emailRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val myReviewsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val plannedEventsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val aboutMeText: MutableState<String> = mutableStateOf(""),
        val phoneText: MutableState<String> = mutableStateOf("+380 (95) 390 86 50"),
        val heightState: MutableState<String> = mutableStateOf(""),
        val weightState: MutableState<String> = mutableStateOf(""),
        val workingLegState: MutableState<String> = mutableStateOf(""),
        val positionState: MutableState<String> = mutableStateOf(""),
        val regionState: MutableState<String> = mutableStateOf(""),
        val cityState: MutableState<String> = mutableStateOf(""),
        val dayBirthdayState: MutableState<String> = mutableStateOf(""),
        val monthBirthdayState: MutableState<String> = mutableStateOf(""),
        val yearBirthdayState: MutableState<String> = mutableStateOf(""),
        val emailStringState: MutableState<String> = mutableStateOf(""),
        val myGenderState: MutableState<String> = mutableStateOf(""),
        val roleState: MutableState<String> = mutableStateOf(""),
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