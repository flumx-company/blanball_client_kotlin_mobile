package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.success.GetUkraineCitiesListResponseEntityData

class MyProfileScreensMainContract {

    sealed class Event : UiEvent {
        object SendGetMyProfileRequest: UiEvent
        object SendEditMyProfileRequest: UiEvent
        object UpdateCitiesForRegionList: UiEvent
        object GetUkraineCitiesList : UiEvent
    }

    data class State(
        val state: ScreenViewState,
        val myAvatarUrl: MutableState<String> = mutableStateOf(""),
        val myFirstNameText: MutableState<String> = mutableStateOf(""),
        val myLastNameText: MutableState<String> = mutableStateOf(""),
        val phoneNumberRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val emailRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val myReviewsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val plannedEventsRadioButtonState: MutableState<Boolean> = mutableStateOf(false),
        val aboutMeText: MutableState<String> = mutableStateOf(""),
        val phoneText: MutableState<String> = mutableStateOf(""),
        val heightState: MutableState<String> = mutableStateOf(""),
        val weightState: MutableState<String> = mutableStateOf(""),
        val workingLegState: MutableState<String> = mutableStateOf(""),
        val positionState: MutableState<String> = mutableStateOf(""),
        val editDayBirthdayState: MutableState<String> = mutableStateOf(""),
        val editMonthBirthdayState: MutableState<String> = mutableStateOf(""),
        val editYearBirthdayState: MutableState<String> = mutableStateOf(""),
        val emailStringState: MutableState<String> = mutableStateOf(""),
        val myGenderState: MutableState<String> = mutableStateOf(""),
        val roleState: MutableState<String> = mutableStateOf(""),
        val birthdayState: MutableState<String> = mutableStateOf(""),
        val placeState: MutableState<String> = mutableStateOf(""),
        val ratingState: MutableState<Float> = mutableStateOf(0f),
        val isModalOpen: MutableState<Boolean> = mutableStateOf(false),
        val isDeleteModalVisible: MutableState<Boolean> = mutableStateOf(false),
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

    sealed class Effect: UiEffect {
        class ShowToast(val message: String): Effect()
    }

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
        object EditProfileRequestSuccess : ScreenViewState()
        object EditProfileRequestError : ScreenViewState()
    }
}