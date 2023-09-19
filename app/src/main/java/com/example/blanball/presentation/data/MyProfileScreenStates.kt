package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class MyProfileScreenMainContract {

    sealed class Event : UiEvent {

    }

    data class State(
        val state: ScreenViewState,
        val myAvatarUrl: MutableState<String> = mutableStateOf("http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg"), //TODO()
        val myFirstNameText: MutableState<String> = mutableStateOf("Женя"), //TODO()
        val myLastNameText: MutableState<String> = mutableStateOf("Жук"), //TODO()
        val phoneNumberRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val emailRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val myReviewsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val plannedEventsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
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