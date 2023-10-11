package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetAllEventResponseEntityResult

class FutureEventsMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val allEventsList: MutableState<List<GetAllEventResponseEntityResult>> = mutableStateOf(
            emptyList()
        ),
        val isLoadingMoreAllEvents: Boolean = false,
        val isAllEventsLoaded: Boolean = false,
        val allEventsCounter: MutableState<Int> = mutableStateOf(0),
        val openFiltersDialog: MutableState<Boolean> = mutableStateOf(false)
        val gendersSelectionState: MutableState<GenderSelectionState> = mutableStateOf(
            GenderSelectionState.ALL
        )
        val typeOfSportState: MutableState<> = mutableStateOf(

        )
    ) : UiState

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {

    }

    enum class GenderSelectionState(val stringValue: String?) {
        ALL(null),
        MALE(Strings.MALE),
        FEMALE(Strings.FEMALE),
    }

    enum class TypeOfSpo
}