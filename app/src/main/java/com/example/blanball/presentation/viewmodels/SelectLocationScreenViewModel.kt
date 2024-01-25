package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.SelectLocationScreenMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.GetUkraineCitiesListResultEntity
import com.example.domain.usecases.interfaces.GetListOfUkraineCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLocationScreenViewModel
@Inject constructor(
    internal val getListOfUkraineCitiesUseCase: GetListOfUkraineCitiesUseCase,
    ) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = SelectLocationScreenMainContract.State(
            state = SelectLocationScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: SelectLocationScreenMainContract.State
        get() = uiState.value as SelectLocationScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is SelectLocationScreenMainContract.Event.GetUkraineCitiesList -> {
                getUkraineCitiesList()
            }
            is SelectLocationScreenMainContract.Event.UpdateCitiesForRegionList -> {
                filterCitiesFromRegion(currentState.selectedRegion.value)
            }
        }
    }

    private fun getUkraineCitiesList() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getListOfUkraineCitiesUseCase.executeGetListOfUkraineCities().let { result ->
                when (result) {
                    is GetUkraineCitiesListResultEntity.Success -> {
                        setState {
                            copy(
                                regionOfUkraineList = mutableStateOf(result.data.data.map { it.name }),
                                locationsData = mutableStateOf(result.data.data.map { it}),
                                citiesForRegionList = mutableStateOf(result.data.data.flatMap { it.cities.map { it.name } }),
                                defaultCitiesForRegionList =  mutableStateOf(result.data.data.flatMap { it.cities.map { it.name } }),
                            )
                        }
                    }
                    is GetUkraineCitiesListResultEntity.Error -> {
                    }
                }
            }
        }
    }

    private fun filterCitiesFromRegion(selectedRegion: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            currentState.citiesForRegionList.value = currentState.defaultCitiesForRegionList.value
            val citiesForRegionList = currentState.citiesForRegionList.value.filter {city ->
                currentState.locationsData.value.any() { it.name == selectedRegion && city in it.cities.map { cityItem -> cityItem.name } }
            }
            currentState.selectedCity.value = citiesForRegionList[0]
            setState {
                copy(
                    citiesForRegionList = mutableStateOf(citiesForRegionList),
                    )
            }
        }
    }

    private fun setState(reduce: SelectLocationScreenMainContract.State.() -> SelectLocationScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}