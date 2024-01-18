package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SelectLocationScreenMainContract
{
    sealed class Event : UiEvent {
        object GetUkraineCitiesList : Event()
    }

    data class State(
        val state: ScreenViewState,
        val isMapExpanded: MutableState<Boolean> = mutableStateOf(false),
        val citiesOfUkraineList: MutableState<List<String>> = mutableStateOf(
            emptyList()
        ),
        val regionsOfUkraineList: MutableState<List<String>> = mutableStateOf(
            emptyList()
        ),
    ) : UiState


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }

}