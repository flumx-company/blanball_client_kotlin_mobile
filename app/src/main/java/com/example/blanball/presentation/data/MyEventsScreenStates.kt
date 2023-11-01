package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetMyEventsResponseEntityResult
import com.example.domain.utils.Strings

class MyEventsScreenMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        val myEventsList: MutableState<List<GetMyEventsResponseEntityResult>> = mutableStateOf(
            emptyList()
        ),
        val isLoadingMoreMyEvents: Boolean = false,
        val isMyEventsLoaded: Boolean = false,
        val myEventsCounter: MutableState<Int> = mutableStateOf(0),
        val openFiltersDialog: MutableState<Boolean> = mutableStateOf(false),
        val gendersSelectionState: MutableState<GenderSelectionState> = mutableStateOf(
            GenderSelectionState.ALL
        ),
        val typeOfEventsStateSelected: MutableState<String> = mutableStateOf(""),
        val typeOfSportsStateSelected: MutableState<String> = mutableStateOf(""),
        val genderSelectionState: MutableState<String> = mutableStateOf(""),
        val eventDatesState: MutableState<String> = mutableStateOf("")
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

    enum class TypeOfSportsStateSelected( val stringValue: String?) {
        ALL(null),
        FOOTBALL(Strings.FOOTBALL),
        FUTSAL(Strings.FUTSAL),
    }
}