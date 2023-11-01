package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.formatSportTypeToEnglish
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.domain.entity.results.GetAllEventsResultEntity
import com.example.domain.usecases.interfaces.GetAllEventsUseCase
import com.example.domain.utils.Integers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FutureEventsScreenViewModel
@Inject constructor(
    internal val getAllEventsUseCase: GetAllEventsUseCase,
    private val application: Application,
) : ViewModel() {
    private var job: Job? = null
    private var page = Integers.ONE

    val defaultState
        get() = FutureEventsMainContract.State(
            state = FutureEventsMainContract.ScreenViewState.Loading,
        )

    val currentState: FutureEventsMainContract.State
        get() = uiState.value as FutureEventsMainContract.State

    private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<FutureEventsMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<FutureEventsMainContract.Effect> = _sideEffect.asSharedFlow()

    internal fun handleScreenState(screenViewState: FutureEventsMainContract.ScreenViewState) {
        when (screenViewState) {
            is FutureEventsMainContract.ScreenViewState.Loading -> {
                setState {
                    copy(
                        allEventsList = mutableStateOf(emptyList()),
                        isAllEventsLoaded = false,
                    )
                }
                page = Integers.ONE
                loadAllEventsList()
            }

            is FutureEventsMainContract.ScreenViewState.LoadingError -> {
            }

            else -> {}
        }
    }

    private fun loadAllEventsList() {
        job = viewModelScope.launch(Dispatchers.IO) {
            val pageToLoad = page
            val typeOfSport =
                currentState.typeOfSportsStateSelected.value.formatSportTypeToEnglish(application.applicationContext)
            val gender =
                currentState.gendersSelectionState.value.stringValue ?: ""
            val time_and_date = currentState.eventDatesState.value
            val ordering = currentState.eventsOrderingSelectionState.value.stringValue ?: ""
            when (val result = getAllEventsUseCase.executeGetAllEvents(
                page = pageToLoad,
                typeOfSport = typeOfSport,
                gender = gender,
                time_and_date = time_and_date,
                ordering = ordering,
            )) {
                is GetAllEventsResultEntity.Success -> {
                    val users = result.success.results
                    users?.let {
                        setState {
                            copy(
                                allEventsList = mutableStateOf(currentState.allEventsList.value + it),
                                isLoadingMoreAllEvents = false,
                                allEventsCounter = mutableStateOf(result.success.total_count),
                                state = FutureEventsMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }
                    }
                    val nextPage = result.success.next
                    if (nextPage.isNullOrEmpty()) {
                        setState {
                            copy(isAllEventsLoaded = true)
                        }
                    }
                }
                is GetAllEventsResultEntity.Error -> {
                    setState {
                        copy(
                            state = FutureEventsMainContract.ScreenViewState.LoadingError,
                        )
                    }
                }
            }
        }
    }

    internal fun loadMoreAllEvents() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreAllEvents || currentState.isAllEventsLoaded)) {
                setState {
                    copy(isLoadingMoreAllEvents = true)
                }
                page++
                loadAllEventsList()
            }
        }
    }

    internal fun setState(reduce: FutureEventsMainContract.State.() -> FutureEventsMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}