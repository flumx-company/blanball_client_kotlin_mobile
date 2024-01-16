package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUkraineCitiesListResponseEntityData

class NavigationDrawerMainContract
{
    sealed class Event : UiEvent {
        object GetLaunchData : Event()
    }

    data class State(
        val state: ScreenViewState,
        val userFirstNameText: MutableState<String> = mutableStateOf(""),
        val userLastNameText: MutableState<String> = mutableStateOf(""),
        val userAvatar: MutableState<String?> = mutableStateOf(""),
        val isDrawerOpen: MutableState<Boolean> = mutableStateOf(false),
        val isSplashScreenActivated: MutableState<Boolean> = mutableStateOf(true),
        val citiesOfUkraineList: MutableState<List<GetUkraineCitiesListResponseEntityData>> = mutableStateOf(
            emptyList()
        ),
    ) : UiState


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

}