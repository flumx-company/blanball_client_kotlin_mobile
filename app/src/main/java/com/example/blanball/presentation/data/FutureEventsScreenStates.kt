package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.blanball.utils.EventTab
import com.example.domain.entity.responses.success.GetAllEventResponseEntityResult
import com.example.domain.utils.Strings

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
        val gendersSelectionState: MutableState<GenderSelectionState> = mutableStateOf(
            GenderSelectionState.ALL
        ),
        val typeOfEventsStateSelected: MutableState<String> = mutableStateOf(""),
        val typeOfSportsStateSelected: MutableState<String> = mutableStateOf(""),
        val genderSelectionState: MutableState<String> = mutableStateOf(""),
        val orderingIconState: MutableState<Boolean> = mutableStateOf(false),
        val eventsOrderingSelectionState: MutableState<EventsOrderingSelectionState> = mutableStateOf(
            EventsOrderingSelectionState.FIRST_NEW
        ),
        val eventDatesState: MutableState<String> = mutableStateOf(""),
        val userFirstNameText: MutableState<String> = mutableStateOf(""),
        val filterDateAndTimeAfter: MutableState<String> = mutableStateOf(""),
        val filterDateAndTimeBefore: MutableState<String> = mutableStateOf(""),
        val selectedEventTab: MutableState<EventTab> = mutableStateOf(EventTab.ALL_EVENTS),
        val isRangeDatePickerModalOpen: MutableState<Boolean> = mutableStateOf(false),
        val isShowEventSuccessCreatedModal: MutableState<Boolean> = mutableStateOf(false),
        val isShowEventSuccessEditModal: MutableState<Boolean> = mutableStateOf(false),
        val isShowProfileSuccessEditModal: MutableState<Boolean> = mutableStateOf(false),
    ) : UiState

    sealed class ScreenViewState {
        object Idle : ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess : ScreenViewState()
        object LoadingError : ScreenViewState()
    }

    sealed class Effect : UiEffect {
    }

    enum class GenderSelectionState(val stringValue: String?) {
        ALL(null),
        MALE(Strings.MALE),
        FEMALE(Strings.FEMALE),
    }

    enum class TypeOfSportsStateSelected(val stringValue: String?) {
        ALL(null),
        FOOTBALL(Strings.FOOTBALL),
        FUTSAL(Strings.FUTSAL),
    }

    enum class EventsOrderingSelectionState(val stringValue: String?) {
        FIRST_NEW(Strings.FIRST_NEW),
        FIRST_OLDER(Strings.FIRST_OLDER)
    }
}