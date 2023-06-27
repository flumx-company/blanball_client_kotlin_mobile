package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class OnboardingScreensStatesMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val dayDropDownState: MutableState<String> = mutableStateOf(""),
        val monthDropDownState: MutableState<String> = mutableStateOf(""),
        val yearDropDownState: MutableState<String> = mutableStateOf(""),
        val selectDocumentState: MutableState<String> = mutableStateOf(""),
        val heightState: MutableState<String> = mutableStateOf(""),
        val weightState: MutableState<String> = mutableStateOf(""),
        val kickingLegState: MutableState<String> = mutableStateOf(""),
        val positionState: MutableState<String> = mutableStateOf(""),
        val cityState: MutableState<String> = mutableStateOf(""),
        val districtState: MutableState<String> = mutableStateOf(""),
        val addDistrictState: MutableState<String> = mutableStateOf(""),
        val footballQualificationsState: MutableState<FootballQualificationsState> = mutableStateOf(FootballQualificationsState.NO_SELECT),
    ) : UiState

    enum class FootballQualificationsState() {
        NO_SELECT,
        INDEPENDENTLY,
        PROFESSIONALLY,
        AMATEURISH,
        DID_N0T_PRACTICE,
}

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }

    data class UiData(
        val progress: Float
    )
}