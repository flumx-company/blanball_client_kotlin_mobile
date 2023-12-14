package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetRelevantUserSearchListResponseEntityResult

class EventCreationScreenMainContract {

    sealed class Event : UiEvent {
        object CreateNewEventClicked : Event()
        object UsersSearchClicked: Event()
    }

    data class State(
        val state: ScreenViewState,
        val eventName: MutableState<String> = mutableStateOf(""),
        val eventType: MutableState<String> = mutableStateOf(""),
        val playersGenderStates: MutableState<PlayersGenderStates> = mutableStateOf(
            PlayersGenderStates.NO_SELECT
        ),
        val timeAndDateOfEvent: MutableState<String> = mutableStateOf(""),
        val placeOfEvent: MutableState<String> = mutableStateOf(""),
        val sportType: MutableState<String> = mutableStateOf(""),
        val entryStates: MutableState<EntryStates> = mutableStateOf(EntryStates.NO_SELECT),
        val contributingStates: MutableState<СontributionsStates> = mutableStateOf(
            СontributionsStates.NO_SELECT
        ),
        val needFormStates: MutableState<NeedFormStates> = mutableStateOf(NeedFormStates.NO_SELECT),
        val phoneNumberState: MutableState<String> = mutableStateOf(""),
        val eventDescriptionState: MutableState<String> = mutableStateOf(""),
        val eventDateState: MutableState<String> = mutableStateOf(""),
        val startEventTimeState: MutableState<String> = mutableStateOf(""),
        val endEventTimeState: MutableState<String> = mutableStateOf(""),
        val maxEventPlayersState: MutableState<String> = mutableStateOf(""),
        val usersSearchState: MutableState<String> = mutableStateOf(""),
        val priseSwitchButtonState: MutableState<Boolean> = mutableStateOf(false),
        val needBallSwitchButtonState: MutableState<Boolean> = mutableStateOf(false),
        val isErrorEventCreation: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessEventCreation: MutableState<Boolean> = mutableStateOf(false),
        val isEventPrivacy: MutableState<EventPrivacyStates> = mutableStateOf(EventPrivacyStates.NO_SELECT),
        val countOfFans: MutableState<Int> = mutableStateOf(0),
        val eventDuration: MutableState<Int> = mutableStateOf(0),
        val listOfFoundUsers: MutableState<List<GetRelevantUserSearchListResponseEntityResult>> = mutableStateOf(
            emptyList()
        ),
        val isSearchColumnOpen: MutableState<Boolean> = mutableStateOf(false),
        val userSearchQuery: MutableState<String> = mutableStateOf(""),
        val selectedUserIds: MutableState<Set<Int>> = mutableStateOf(emptySet()),
        val selectedUserProfiles: MutableState<Set<GetRelevantUserSearchListResponseEntityResult>> = mutableStateOf(
            emptySet()
        ),
    ) : UiState

    sealed class ScreenViewState {
        object Idle : ScreenViewState()
        object Loading : ScreenViewState()
        object UserSearchRequestSuccess: ScreenViewState()
        object UserSearchLoading: ScreenViewState()
        object UserSearchRequestError: ScreenViewState()
        object SuccessRequest : ScreenViewState()
        object ErrorRequest : ScreenViewState()
    }

    sealed class Effect : UiEffect {

    }

    enum class PlayersGenderStates() {
        NO_SELECT,
        MANS,
        WOMANS,
    }

    enum class EntryStates {
        NO_SELECT,
        FREE_ENTRY,
        CLOSE_ENTRY,
    }

    enum class СontributionsStates {
        NO_SELECT,
        FREE,
        PAID,
    }

    enum class NeedFormStates {
        NO_SELECT,
        YES,
        NO,
    }

    enum class EventPrivacyStates {
        NO_SELECT,
        YES,
        NO,
    }
}