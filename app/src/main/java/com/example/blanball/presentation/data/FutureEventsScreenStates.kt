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
        val openFiltersDialog: MutableState<Boolean> = mutableStateOf(false),
        val gendersSelectionState: MutableState<GendersSelectionState> = mutableStateOf(
            GendersSelectionState.ALL
        ),
        val typeOfEventsStateSelected: MutableState<String> = mutableStateOf(""),
        val typeOfSportsStateSelected: MutableState<String> = mutableStateOf(""),
        val freeOrPaidStateSelected: MutableState<FreeOrPaidState> = mutableStateOf(FreeOrPaidState.NO_SELECTED),

    ) : UiState

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess: ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {

    }

    enum class FreeOrPaidState() {
        NO_SELECTED(),
        FREE(),
        PAID(),
    }

    enum class GendersSelectionState() {
        ALL(),
        MALE(),
        FEMALE(),
    }

    enum class TypeOfSpo
}