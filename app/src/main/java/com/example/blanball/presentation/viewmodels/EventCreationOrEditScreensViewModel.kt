package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EventEditAndCreationScreensMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.EventPrivacyStatesToBoolean
import com.example.blanball.utils.ext.NeedFormStatesToBoolean
import com.example.blanball.utils.ext.PlayersGenderStatesToString
import com.example.blanball.utils.ext.SportTypesStringsToEnglish
import com.example.blanball.utils.ext.formatToIso8601DateTime
import com.example.blanball.utils.ext.getAddressFromLocation
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.entity.results.GetRelevantUserSearchListResultEntity
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
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
class EventCreationOrEditScreensViewModel
@Inject constructor(
    internal val creationNewEventUseCase: CreationAnEventUseCase,
    internal val searchListUseCase: GetRelevantUserSearchListUseCase,
    internal val editEventUseCase: EditEventByIdUseCase,
    private val application: Application,
) : ViewModel() {
    private var job: Job? = null
    val defaultState
        get() = EventEditAndCreationScreensMainContract.State(
            state = EventEditAndCreationScreensMainContract.ScreenViewState.Idle,
        )

    val currentState: EventEditAndCreationScreensMainContract.State
        get() = uiState.value as EventEditAndCreationScreensMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<EventEditAndCreationScreensMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<EventEditAndCreationScreensMainContract.Effect> =
        _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EventEditAndCreationScreensMainContract.Event.CreateNewEventClicked -> {
                setState {
                    copy(
                        state = EventEditAndCreationScreensMainContract.ScreenViewState.Loading
                    )
                }
                requestCreationNewEvent()
            }

            is EventEditAndCreationScreensMainContract.Event.EditEventClicked -> {
                setState {
                    copy(
                        state = EventEditAndCreationScreensMainContract.ScreenViewState.Loading
                    )
                }
                requestEditEvent()
            }

            is EventEditAndCreationScreensMainContract.Event.UsersSearchClicked -> {
                job = viewModelScope.launch(Dispatchers.Default) {
                    setState {
                        copy(
                            state = EventEditAndCreationScreensMainContract.ScreenViewState.UserSearchLoading,
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
                                    state = EventEditAndCreationScreensMainContract.ScreenViewState.UserSearchRequestSuccess,
                                )
                            }
                        }
                    }

                    is GetRelevantUserSearchListResultEntity.Error -> {
                        setState {
                            copy(
                                state = EventEditAndCreationScreensMainContract.ScreenViewState.UserSearchRequestError,
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
                id = currentState.currentEventId.value,
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
                                state = EventEditAndCreationScreensMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(
                                    EventEditAndCreationScreensMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventEditAndCreationScreensMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventEditAndCreationScreensMainContract.NeedFormStates.NO_SELECT
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
                                state = EventEditAndCreationScreensMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(
                                    EventEditAndCreationScreensMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventEditAndCreationScreensMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventEditAndCreationScreensMainContract.NeedFormStates.NO_SELECT
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
                                state = EventEditAndCreationScreensMainContract.ScreenViewState.ErrorRequest,
                                isErrorEventCreation = mutableStateOf(true),
                            )
                        }
                    }
                }
            }
        }
    }

    internal fun setState(reduce: EventEditAndCreationScreensMainContract.State.() -> EventEditAndCreationScreensMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}