package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUkraineCitiesListResponseEntityData

class OnboardingScreensStatesMainContract {

    sealed class Event : UiEvent {
       object FinishFillingOutTheProfileClicked : Event()
        object UpdateCitiesForRegionList: UiEvent
        object GetUkraineCitiesList : UiEvent
    }

    data class State(
        val state: ScreenViewState,
        val dayBirthdayState: MutableState<String> = mutableStateOf(""),
        val monthBirthdayState: MutableState<String> = mutableStateOf(""),
        val yearBirthdayState: MutableState<String> = mutableStateOf(""),
        val selectDocumentState: MutableState<String> = mutableStateOf(""),
        val heightState: MutableState<String> = mutableStateOf(""),
        val weightState: MutableState<String> = mutableStateOf(""),
        val workingLegState: MutableState<String> = mutableStateOf(""),
        val positionState: MutableState<String> = mutableStateOf(""),
        val addDistrictState: MutableState<String> = mutableStateOf(""),
        val footballQualificationsState: MutableState<FootballQualificationsState> = mutableStateOf(FootballQualificationsState.NO_SELECT),
        val isErrorRequestToFinishOutTheProfile: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessRequestToFinishOutTheProfile: MutableState<Boolean> = mutableStateOf(false),
        val selectedRegion: MutableState<String> = mutableStateOf(""),
        val selectedCity: MutableState<String> = mutableStateOf(""),
        val regionOfUkraineList: MutableState<List<String>> = mutableStateOf(emptyList()),
        val citiesForRegionList: MutableState<List<String>> = mutableStateOf(
            emptyList()
        ),
        val defaultCitiesForRegionList: MutableState<List<String>> = mutableStateOf(
            emptyList()
        ),
        val locationsData: MutableState<List<GetUkraineCitiesListResponseEntityData>> = mutableStateOf(
            emptyList()
        )
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
        object SuccessFinishFillingOutTheProfile : ScreenViewState()
        object ErrorFinishFillingOutTheProfile : ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }

    data class UiData(
        val progress: Float
    )
}