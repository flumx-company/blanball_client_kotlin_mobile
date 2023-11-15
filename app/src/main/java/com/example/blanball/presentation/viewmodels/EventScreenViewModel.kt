package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventScreenViewModel
@Inject constructor(
    internal val getEventByIdUseCase: GetEventByIdUseCase,
) : ViewModel() {
    private var job: Job? = null

    val defaultState
        get() = EventScreenMainContract.State(
            state = EventScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: EventScreenMainContract.State
        get() = uiState.value as EventScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    internal fun loadUEventData() {
        viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Loading
                )
            }
            currentState.currentEventId.value.let {
                getEventById(
                    userId = it
                )
            }
        }
    }

    private fun getEventById(userId: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            getEventByIdUseCase.executeGetEventById(userId).let {
                when (it) {
                    is GetEventByIdResultEntity.Success ->
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.LoadingSuccess,

                                )
                        }

                    is GetEventByIdResultEntity.Error -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.LoadingError
                            )
                        }
                    }
                }
            }
        }
    }

    fun setState(reduce: EventScreenMainContract.State.() -> EventScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}