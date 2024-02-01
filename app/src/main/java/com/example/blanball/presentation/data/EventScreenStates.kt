package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetRelevantUserSearchListResponseEntityResult
import com.google.android.gms.maps.model.LatLng

class EventScreenMainContract {

    sealed class Event : UiEvent {
        object CreateNewEventClicked : Event()
        object UsersSearchClicked : Event()
        object EditEventClicked : Event()
        object CleanStates: Event()
        object LoadEventData: Event()
    }

    data class State(
        val state: ScreenViewState,
        val currentEventId: MutableState<Int?> = mutableStateOf(null),
        val eventName: MutableState<String> = mutableStateOf(""),
        val sportType: MutableState<String> = mutableStateOf(""),
        val eventDateAndTime: MutableState<String> = mutableStateOf(""),
        val eventDuration: MutableState<Int> = mutableStateOf(0),
        val eventPlaceName: MutableState<String> = mutableStateOf(""),
        val eventDescription: MutableState<String> = mutableStateOf(""),
        val eventGenders: MutableState<String> = mutableStateOf(""),
        val eventAuthorFirstName: MutableState<String> = mutableStateOf(""),
        val eventAuthorLastName: MutableState<String> = mutableStateOf(""),
        val eventAuthorPhone: MutableState<String> = mutableStateOf(""),
        val eventAuthorAvatar: MutableState<String> = mutableStateOf(""),
        val eventPrice: MutableState<Int> = mutableStateOf(0),
        val isMyEvent: MutableState<Boolean> = mutableStateOf(false),
        val isDescriptionTextExpanded: MutableState<Boolean> = mutableStateOf(false),
        val currentEventAuthorId: MutableState<Int> = mutableStateOf(0),
        val priceDescription: MutableState<String?> = mutableStateOf(null),
        val isEventDescriptionVisible: MutableState<Boolean> = mutableStateOf(false),
        val eventLatLng: MutableState<LatLng> = mutableStateOf(
            LatLng(
                50.45074559462868,
                30.523837655782696
            )
        ),
        val eventType: MutableState<String> = mutableStateOf(""),
        val playersGenderStates: MutableState<EventScreenMainContract.PlayersGenderStates> = mutableStateOf(
            EventScreenMainContract.PlayersGenderStates.NO_SELECT
        ),
        val priceStates: MutableState<EventScreenMainContract.PriceStates> = mutableStateOf(
            EventScreenMainContract.PriceStates.NO_SELECT),
        val needFormStates: MutableState<EventScreenMainContract.NeedFormStates> = mutableStateOf(
            EventScreenMainContract.NeedFormStates.NO_SELECT),
        val phoneNumberState: MutableState<String> = mutableStateOf(""),
        val eventDateState: MutableState<String> = mutableStateOf(""),
        val startEventTimeState: MutableState<String> = mutableStateOf(""),
        val endEventTimeState: MutableState<String> = mutableStateOf(""),
        val maxEventPlayersState: MutableState<String> = mutableStateOf(""),
        val usersSearchState: MutableState<String> = mutableStateOf(""),
        val priseSwitchButtonState: MutableState<Boolean> = mutableStateOf(false),
        val needBallSwitchButtonState: MutableState<Boolean> = mutableStateOf(false),
        val isErrorEventCreation: MutableState<Boolean> = mutableStateOf(false),
        val isErrorEventEdit: MutableState<Boolean> = mutableStateOf(false),
        val isSuccessEventCreation: MutableState<Boolean> = mutableStateOf(false),
        val isEventPrivacyStates: MutableState<EventPrivacyStates> = mutableStateOf(
            EventPrivacyStates.NO_SELECT),
        val countOfFans: MutableState<Int> = mutableStateOf(0),
        val isEventPrivate: MutableState<Boolean> = mutableStateOf(false),
        val isFormNeed: MutableState<Boolean> = mutableStateOf(false),
        val listOfFoundUsers: MutableState<List<GetRelevantUserSearchListResponseEntityResult>> = mutableStateOf(
            emptyList()
        ),
        val isSearchColumnOpen: MutableState<Boolean> = mutableStateOf(false),
        val userSearchQuery: MutableState<String> = mutableStateOf(""),
        val selectedUserIds: MutableState<Set<Int>> = mutableStateOf(emptySet()),
        val selectedUserProfiles: MutableState<Set<GetRelevantUserSearchListResponseEntityResult>> = mutableStateOf(
            emptySet()
        ),
        val isValidationActivated: MutableState<Boolean> = mutableStateOf(false),
        val eventLocationLatLng: MutableState<LatLng> = mutableStateOf(
            LatLng(
                50.45074559462868,
                30.523837655782696
            )
        ),
        val isEditOrCreation: MutableState<EventScreenMainContract.EditOrCreationState> = mutableStateOf(
            EventScreenMainContract.EditOrCreationState.CREATION),
        val isInvitedUsersDrawerOpen: MutableState<Boolean> = mutableStateOf(false),
        val isBottomPreviewDrawerOpen: MutableState<Boolean> = mutableStateOf(false),
        val isStartEventTimeModalOpen: MutableState<Boolean> = mutableStateOf(false),
        val isDatePickerModalOpen: MutableState<Boolean> = mutableStateOf(false),
        val eventSummaryPrice: MutableState<String?> = mutableStateOf(null),
        val selectRegion: MutableState<String> = mutableStateOf(""),
        val selectCity: MutableState<String> = mutableStateOf(""),
    ) : UiState

    sealed class ScreenViewState {
        object Idle : ScreenViewState()
        object Loading : ScreenViewState()
        object LoadingSuccess : ScreenViewState()
        object LoadingError : ScreenViewState()
        object UserSearchRequestSuccess : ScreenViewState()
        object UserSearchLoading : ScreenViewState()
        object UserSearchRequestError : ScreenViewState()
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

    enum class PriceStates {
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

    enum class EditOrCreationState {
        EDIT,
        CREATION,
    }
}