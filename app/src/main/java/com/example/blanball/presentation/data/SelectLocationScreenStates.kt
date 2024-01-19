package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUkraineCitiesListResponseEntityData

class SelectLocationScreenMainContract
{
    sealed class Event : UiEvent {
        object GetUkraineCitiesList : Event()
        object UpdateCitiesForRegionList: Event()
    }

    data class State(
        val state: ScreenViewState,
        val isMapExpanded: MutableState<Boolean> = mutableStateOf(false),
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


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object Idle : ScreenViewState()
    }
}