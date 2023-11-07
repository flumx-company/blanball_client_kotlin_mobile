package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.MyEventsScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.formatSportTypeToEnglish
import com.example.domain.entity.results.GetMyEventsResultEntity
import com.example.domain.usecases.interfaces.GetMyEventsUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsScreenViewModel
@Inject constructor(
    internal val getMyEventsUseCase: GetMyEventsUseCase,
    private val application: Application,
) : ViewModel() {
    private var job: Job? = null
    private var page = Integers.ONE

    val defaultState
        get() = MyEventsScreenMainContract.State(
            state = MyEventsScreenMainContract.ScreenViewState.Loading,
        )

    val currentState: MyEventsScreenMainContract.State
        get() = uiState.value as MyEventsScreenMainContract.State

    private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<MyEventsScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MyEventsScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    internal fun handleScreenState(screenViewState: MyEventsScreenMainContract.ScreenViewState) {
        when (screenViewState) {
            is MyEventsScreenMainContract.ScreenViewState.Loading -> {
                setState {
                    copy(
                        myEventsList = mutableStateOf(emptyList()),
                        isMyEventsLoaded = false,
                    )
                }
                page = Integers.ONE
                loadMyEventsList()
            }
            is MyEventsScreenMainContract.ScreenViewState.LoadingError -> {
            }
            else -> {}
        }
    }

    private fun loadMyEventsList() {
        job = viewModelScope.launch(Dispatchers.IO) {
            val pageToLoad = page
            val typeOfSport =
                currentState.typeOfSportsStateSelected.value.formatSportTypeToEnglish(application.applicationContext)
            val gender =
                currentState.gendersSelectionState.value.stringValue ?: ""
            val time_and_date = currentState.eventDatesState.value
            val ordering = currentState.myEventsOrderingSelectionState.value.stringValue ?: ""
            val filterDateAndTimeAfter = currentState.filterDateAndTimeAfter.value
            val filterDateAndTimeBefore = currentState.filterDateAndTimeBefore.value
            when (val result = getMyEventsUseCase.executeMyEventsEvents(
                page = pageToLoad,
                typeOfSport = typeOfSport,
                gender = gender,
                time_and_date = time_and_date,
                ordering = ordering,
                filterDateAndTimeAfter = filterDateAndTimeAfter,
                filterDateAndTimeBefore = filterDateAndTimeBefore,
            )) {
                is GetMyEventsResultEntity.Success -> {
                    val users = result.success.results
                    users?.let {
                        setState {
                            copy(
                                myEventsList = mutableStateOf(currentState.myEventsList.value + it),
                                isLoadingMoreMyEvents = false,
                                myEventsCounter = mutableStateOf(result.success.total_count),
                                state = MyEventsScreenMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }
                    }
                    val nextPage = result.success.next
                    if (nextPage.isNullOrEmpty()) {
                        setState {
                            copy(isMyEventsLoaded = true)
                        }
                    }
                }
                is GetMyEventsResultEntity.Error -> {
                    setState {
                        copy(
                            state = MyEventsScreenMainContract.ScreenViewState.LoadingError,
                        )
                    }
                }
            }
        }
    }

    internal fun loadMoreMyEvents() {
        job = viewModelScope.launch(Dispatchers.IO) {
            if (!(currentState.isLoadingMoreMyEvents || currentState.isMyEventsLoaded)) {
                setState {
                    copy(isLoadingMoreMyEvents = true)
                }
                page++
                loadMyEventsList()
            }
        }
    }

    internal fun setState(reduce: MyEventsScreenMainContract.State.() -> MyEventsScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}