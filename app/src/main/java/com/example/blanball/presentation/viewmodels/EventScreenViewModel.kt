package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.EventPrivacyStatesToBoolean
import com.example.blanball.utils.ext.NeedFormStatesToBoolean
import com.example.blanball.utils.ext.PlayersGenderStatesToString
import com.example.blanball.utils.ext.SportTypesStringsToEnglish
import com.example.blanball.utils.ext.formatToIso8601DateTime
import com.example.blanball.utils.ext.getAddressFromLocation
import com.example.data.datastore.useridmanager.UserIdManager
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.entity.results.GetRelevantUserSearchListResultEntity
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventScreenViewModel
@Inject constructor(
    internal val getEventByIdUseCase: GetEventByIdUseCase,
    val userIdManager: UserIdManager,
    internal val creationNewEventUseCase: CreationAnEventUseCase,
    internal val searchListUseCase: GetRelevantUserSearchListUseCase,
    internal val editEventUseCase: EditEventByIdUseCase,
    private val application: Application,
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

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EventScreenMainContract.Event.CreateNewEventClicked -> {
                setState {
                    copy(
                        state = EventScreenMainContract.ScreenViewState.Loading
                    )
                }
                requestCreationNewEvent()
            }

            is EventScreenMainContract.Event.EditEventClicked -> {
                setState {
                    copy(
                        state = EventScreenMainContract.ScreenViewState.Loading
                    )
                }
                requestEditEvent()
            }

            is EventScreenMainContract.Event.UsersSearchClicked -> {
                job = viewModelScope.launch(Dispatchers.Default) {
                    setState {
                        copy(
                            state = EventScreenMainContract.ScreenViewState.UserSearchLoading,
                        )
                    }
                    userSearch()
                }
            }
        }
    }

    private fun userSearch() {
        job = viewModelScope.launch(Dispatchers.IO) {
            searchListUseCase.executeGetRelevantUserSearchList(
                search = currentState.userSearchQuery.value,
                page = 1,
                skipids = "",
            ).let { result ->
                when (result) {
                    is GetRelevantUserSearchListResultEntity.Success -> {
                        val users = result.data.results
                        users.let {
                            setState {
                                copy(
                                    listOfFoundUsers = mutableStateOf(it),
                                    state = EventScreenMainContract.ScreenViewState.UserSearchRequestSuccess,
                                )
                            }
                        }
                    }

                    is GetRelevantUserSearchListResultEntity.Error -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.UserSearchRequestError,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun requestEditEvent() {
        job = viewModelScope.launch(Dispatchers.IO) {
            editEventUseCase.executeEditEventById(
                id = currentState.currentEventId.value ?: 0,
                amount_members = currentState.maxEventPlayersState.value.toInt(),
                contact_number = currentState.phoneNumberState.value,
                date_and_time = formatToIso8601DateTime(
                    date = currentState.eventDateState.value,
                    time = currentState.startEventTimeState.value
                ),
                description = currentState.eventDescriptionState.value,
                duration = currentState.eventDuration.value,
                gender = currentState.playersGenderStates.value.PlayersGenderStatesToString(context = application.applicationContext),
                hidden = false,
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value.NeedFormStatesToBoolean(),
                place_name = currentState.eventLocationLatLng.value.getAddressFromLocation(context = application.applicationContext)
                    ?: "",
                lat = currentState.eventLocationLatLng.value.latitude,
                lon = currentState.eventLocationLatLng.value.longitude,
                price = currentState.eventSummaryPrice.value?.toIntOrNull(),
                price_description = currentState.priceDescription.value,
                privacy = currentState.isEventPrivacy.value.EventPrivacyStatesToBoolean(),
                type = currentState.sportType.value.SportTypesStringsToEnglish(context = application.applicationContext),
            ).let {
                when (it) {
                    is EditEventByIdResultEntity.Success -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(
                                    EventScreenMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventScreenMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventScreenMainContract.NeedFormStates.NO_SELECT
                                ),
                                phoneNumberState = mutableStateOf(""),
                                eventDescriptionState = mutableStateOf(""),
                                startEventTimeState = mutableStateOf(""),
                                endEventTimeState = mutableStateOf(""),
                                maxEventPlayersState = mutableStateOf(""),
                                usersSearchState = mutableStateOf(""),
                                priseSwitchButtonState = mutableStateOf(false),
                                needBallSwitchButtonState = mutableStateOf(false),
                                listOfFoundUsers = mutableStateOf(emptyList()),
                                selectedUserIds = mutableStateOf(emptySet()),
                                selectedUserProfiles = mutableStateOf(emptySet()),
                                priceDescription = mutableStateOf(null),
                            )
                        }
                    }

                    is EditEventByIdResultEntity.Error -> {
                        setState {
                            copy(
                                isErrorEventEdit = mutableStateOf(true)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun requestCreationNewEvent() {
        job = viewModelScope.launch(Dispatchers.IO) {
            creationNewEventUseCase.executeCreationAnEvent(
                amount_members = currentState.maxEventPlayersState.value.toInt(),
                contact_number = currentState.phoneNumberState.value,
                current_users = currentState.selectedUserIds.value.toList(),
                date_and_time = formatToIso8601DateTime(
                    date = currentState.eventDateState.value,
                    time = currentState.startEventTimeState.value
                ),
                description = currentState.eventDescriptionState.value,
                duration = currentState.eventDuration.value,
                gender = currentState.playersGenderStates.value.PlayersGenderStatesToString(context = application.applicationContext),
                hidden = false,
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value.NeedFormStatesToBoolean(),
                place = currentState.eventLocationLatLng.value.getAddressFromLocation(context = application.applicationContext)
                    ?: "",
                lat = currentState.eventLocationLatLng.value.latitude,
                lon = currentState.eventLocationLatLng.value.longitude,
                price = currentState.eventSummaryPrice.value?.toIntOrNull(),
                price_description = currentState.priceDescription.value,
                privacy = currentState.isEventPrivacy.value.EventPrivacyStatesToBoolean(),
                type = currentState.sportType.value.SportTypesStringsToEnglish(context = application.applicationContext),
            ).let {
                when (it) {
                    is CreationAnEventResultEntity.Success -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(
                                    EventScreenMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventScreenMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventScreenMainContract.NeedFormStates.NO_SELECT
                                ),
                                phoneNumberState = mutableStateOf(""),
                                eventDescriptionState = mutableStateOf(""),
                                startEventTimeState = mutableStateOf(""),
                                endEventTimeState = mutableStateOf(""),
                                maxEventPlayersState = mutableStateOf(""),
                                usersSearchState = mutableStateOf(""),
                                priseSwitchButtonState = mutableStateOf(false),
                                needBallSwitchButtonState = mutableStateOf(false),
                                listOfFoundUsers = mutableStateOf(emptyList()),
                                selectedUserIds = mutableStateOf(emptySet()),
                                selectedUserProfiles = mutableStateOf(emptySet()),
                                priceDescription = mutableStateOf(null),
                            )
                        }
                    }

                    is CreationAnEventResultEntity.Error -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.ErrorRequest,
                                isErrorEventCreation = mutableStateOf(true),
                            )
                        }
                    }
                }
            }
        }
    }

    internal fun loadUEventData() {
        viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Loading
                )
            }
            currentState.currentEventId.value.let { currentEventId ->
                currentEventId?.let { it ->
                    getEventById(
                        eventId = it
                    )
                }
            }
        }
    }

    private fun getEventById(eventId: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val userIdResult = userIdManager.getUserId().firstOrNull()
            getEventByIdUseCase.executeGetEventById(eventId).let {
                when (it) {
                    is GetEventByIdResultEntity.Success ->
                        setState {
                            copy(
                                eventName = mutableStateOf(it.data.name),
                                sportType = mutableStateOf(it.data.type),
                                eventDateAndTime = mutableStateOf(it.data.date_and_time),
                                eventDuration = mutableStateOf(it.data.duration),
                                eventPlaceName = mutableStateOf(it.data.place.place_name),
                                eventDescription = mutableStateOf(it.data.description),
                                eventGenders = mutableStateOf(it.data.gender),
                                eventAuthorFirstName = mutableStateOf(it.data.author.profile.name),
                                eventAuthorLastName = mutableStateOf(it.data.author.profile.last_name),
                                eventAuthorPhone = mutableStateOf(it.data.author.phone),
                                eventAuthorAvatar = mutableStateOf(
                                    it.data.author.profile.avatar_url ?: ""
                                ),
                                currentEventAuthorId = mutableStateOf(it.data.author.id),
                                eventPrice = mutableStateOf(it.data.price ?: 0),
                                isMyEvent = mutableStateOf(it.data.author.profile.id == userIdResult),
                                priceDescription = mutableStateOf(it.data.price_description),
                                eventLatLng = mutableStateOf(LatLng(it.data.place.lat, it.data.place.lon)),
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