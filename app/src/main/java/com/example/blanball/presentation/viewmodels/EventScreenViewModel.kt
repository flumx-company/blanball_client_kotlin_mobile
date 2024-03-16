package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.R
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.EventPrivacyStatesToBoolean
import com.example.blanball.utils.ext.NeedFormStatesToBoolean
import com.example.blanball.utils.ext.PlayersGenderStatesToString
import com.example.blanball.utils.ext.SportTypesStringsToEnglish
import com.example.blanball.utils.ext.checkNullIntPriceValue
import com.example.blanball.utils.ext.formatToIso8601DateTime
import com.example.blanball.utils.ext.getAddressFromLocation
import com.example.blanball.utils.ext.mapFormStatesOnEditScreen
import com.example.blanball.utils.ext.mapGenderOnEditScreen
import com.example.blanball.utils.ext.mapPriceOnEditScreen
import com.example.blanball.utils.ext.mapPrivacyOnEditScreen
import com.example.blanball.utils.ext.mapToDateOnEditScreen
import com.example.blanball.utils.ext.mapToTimeOnEditScreen
import com.example.data.datastore.useridmanager.UserIdManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.entity.results.GetRelevantUserSearchListResultEntity
import com.example.domain.entity.results.JoinToEventAsPlayerResultEntity
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
import com.example.domain.usecases.interfaces.JoinToEventAsFunUseCase
import com.example.domain.usecases.interfaces.JoinToEventAsPlayerUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventScreenViewModel
@Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val userIdManager: UserIdManager,
    private val userPhoneManager: UserPhoneManager,
    private val creationNewEventUseCase: CreationAnEventUseCase,
    private val searchListUseCase: GetRelevantUserSearchListUseCase,
    private val editEventUseCase: EditEventByIdUseCase,
    private val joinToEventAsFunUseCase: JoinToEventAsFunUseCase,
    private val joinToEventAsPlayerUseCase: JoinToEventAsPlayerUseCase,
    private val application: Application,

    ) : ViewModel() {
    private var job: Job? = null

    private var dataStoreJob = SupervisorJob()
    private var dataStoreCoroutineScope = CoroutineScope(dataStoreJob)

    val defaultState
        get() = EventScreenMainContract.State(
            state = EventScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: EventScreenMainContract.State
        get() = uiState.value as EventScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    sealed class ParticipantRole {
        object PLAYER : ParticipantRole()
        object FUN : ParticipantRole()
    }

    sealed class EventToastType {
        object SUCCESS : EventToastType()
        object ERROR : EventToastType()
    }

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EventScreenMainContract.Event.LoadEventData -> {
                loadEventData()
            }

            is EventScreenMainContract.Event.GetUserPhone -> {
                getUserPhoneFromDataStore()
            }

            is EventScreenMainContract.Event.CleanStates -> {
                cleanStates()
            }

            is EventScreenMainContract.Event.SuccessfullyJoinToEvent -> {
                showToastMessage(toastType = EventToastType.SUCCESS, durationMillis = 3000)
            }

            is EventScreenMainContract.Event.ErrorJoinToEvent -> {
                showToastMessage(toastType = EventToastType.ERROR, durationMillis = 3000)
            }

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

            is EventScreenMainContract.Event.JoinToEventAsPlayer -> {
                joinToEvent(asWho = ParticipantRole.PLAYER)
            }

            is EventScreenMainContract.Event.JoinToEventAsFun -> {
                joinToEvent(asWho = ParticipantRole.FUN)
            }
        }
    }

    private fun cleanStates() {
        job = viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    eventName = mutableStateOf(""),
                    eventType = mutableStateOf(""),
                    eventDateState = mutableStateOf(""),
                    eventDateAndTime = mutableStateOf(""),
                    startEventTimeState = mutableStateOf(""),
                    playersGenderStates = mutableStateOf(
                        EventScreenMainContract.PlayersGenderStates.NO_SELECT
                    ),
                    sportType = mutableStateOf(""),
                    priceStates = mutableStateOf(EventScreenMainContract.PriceStates.NO_SELECT),
                    needFormStates = mutableStateOf(
                        EventScreenMainContract.NeedFormStates.NO_SELECT
                    ),
                    eventGenders = mutableStateOf(""),
                    endEventTimeState = mutableStateOf(""),
                    maxEventPlayersState = mutableStateOf(""),
                    usersSearchState = mutableStateOf(""),
                    priseSwitchButtonState = mutableStateOf(false),
                    needBallSwitchButtonState = mutableStateOf(false),
                    listOfFoundUsers = mutableStateOf(emptyList()),
                    selectedUserIds = mutableStateOf(emptySet()),
                    selectedUserProfiles = mutableStateOf(emptySet()),
                    priceDescription = mutableStateOf(null),
                    isPhoneNumInputEnabled = mutableStateOf(false),
                    currentEventId = mutableStateOf(null),
                    invitedPlayersList = mutableStateOf(
                        emptyList()
                    ),
                    invitedFunsList = mutableStateOf(
                        emptyList()
                    ),
                    eventDuration = mutableIntStateOf(0),
                    eventPlaceName = mutableStateOf(""),
                    eventDescription = mutableStateOf(""),
                    eventAuthorFirstName = mutableStateOf(""),
                    eventAuthorLastName = mutableStateOf(""),
                    eventAuthorPhone = mutableStateOf(""),
                    eventAuthorAvatar = mutableStateOf(""),
                    eventPrice = mutableIntStateOf(0),
                    isMyEvent = mutableStateOf(false),
                    isDescriptionTextExpanded = mutableStateOf(false),
                    currentEventAuthorId = mutableIntStateOf(0),
                    isEventDescriptionVisible = mutableStateOf(false),
                    eventLatLng = mutableStateOf(
                        LatLng(
                            50.45074559462868,
                            30.523837655782696
                        ),
                    ),
                    isErrorEventCreation = mutableStateOf(false),
                    isErrorEventEdit = mutableStateOf(false),
                    isSuccessEventEdit = mutableStateOf(false),
                    isSuccessEventCreation = mutableStateOf(false),
                    isEventPrivacyStates = mutableStateOf(
                        EventScreenMainContract.EventPrivacyStates.NO_SELECT
                    ),
                    countOfFans = mutableIntStateOf(0),
                    isEventPrivate = mutableStateOf(false),
                    isFormNeed = mutableStateOf(false),
                    isSearchColumnOpen = mutableStateOf(false),
                    userSearchQuery = mutableStateOf(""),
                    isValidationActivated = mutableStateOf(false),
                    eventLocationLatLng = mutableStateOf(
                        LatLng(
                            50.45074559462868,
                            30.523837655782696
                        )
                    ),
                    isInvitedUsersDrawerOpen = mutableStateOf(false),
                    isBottomPreviewDrawerOpen = mutableStateOf(false),
                    isStartEventTimeModalOpen = mutableStateOf(false),
                    isDatePickerModalOpen = mutableStateOf(false),
                    eventSummaryPrice = mutableStateOf(null),
                    selectRegion = mutableStateOf(""),
                    selectCity = mutableStateOf(""),
                    successMessageText = mutableStateOf(""),
                    isSuccessMessageVisible = mutableStateOf(false),
                    isUserHasBeenJoinedToEvent = mutableStateOf(false),
                )
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
                contact_number = application.getString(R.string.ua_phone_country_code) + currentState.phoneNumberState.value,
                date_and_time = formatToIso8601DateTime(
                    date = currentState.eventDateState.value,
                    time = currentState.startEventTimeState.value
                ),
                description = currentState.eventDescription.value,
                duration = currentState.eventDuration.value,
                gender = currentState.playersGenderStates.value.PlayersGenderStatesToString(context = application.applicationContext),
                hidden = currentState.isEventPrivacyStates.value.EventPrivacyStatesToBoolean(),
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value.NeedFormStatesToBoolean(),
                place_name = currentState.eventLocationLatLng.value.getAddressFromLocation(context = application.applicationContext)
                    ?: "",
                lat = currentState.eventLocationLatLng.value.latitude,
                lon = currentState.eventLocationLatLng.value.longitude,
                price = currentState.eventSummaryPrice.value?.toIntOrNull(),
                price_description = currentState.priceDescription.value,
                privacy = currentState.isEventPrivacyStates.value.EventPrivacyStatesToBoolean(),
                type = currentState.sportType.value.SportTypesStringsToEnglish(context = application.applicationContext),
            ).let {
                when (it) {
                    is EditEventByIdResultEntity.Success -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventEdit = mutableStateOf(true),
                                isErrorEventEdit = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventSummaryPrice = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(
                                    EventScreenMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventScreenMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventScreenMainContract.NeedFormStates.NO_SELECT
                                ),
                                isEventPrivacyStates = mutableStateOf(EventScreenMainContract.EventPrivacyStates.NO_SELECT),
                                phoneNumberState = mutableStateOf(""),
                                eventDescription = mutableStateOf(""),
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
                contact_number = application.getString(R.string.ua_phone_country_code) + currentState.phoneNumberState.value,
                current_users = currentState.selectedUserIds.value.toList(),
                date_and_time = formatToIso8601DateTime(
                    date = currentState.eventDateState.value,
                    time = currentState.startEventTimeState.value
                ),
                description = currentState.eventDescription.value,
                duration = currentState.eventDuration.value,
                gender = currentState.playersGenderStates.value.PlayersGenderStatesToString(context = application.applicationContext),
                hidden = currentState.isEventPrivacyStates.value.EventPrivacyStatesToBoolean(),
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value.NeedFormStatesToBoolean(),
                place = currentState.eventLocationLatLng.value.getAddressFromLocation(context = application.applicationContext)
                    ?: "",
                lat = currentState.eventLocationLatLng.value.latitude,
                lon = currentState.eventLocationLatLng.value.longitude,
                price = currentState.eventSummaryPrice.value?.toIntOrNull(),
                price_description = currentState.priceDescription.value,
                privacy = currentState.isEventPrivacyStates.value.EventPrivacyStatesToBoolean(),
                type = currentState.sportType.value.SportTypesStringsToEnglish(context = application.applicationContext),
                forms = CreationAnEventResponseEntityForms(),
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
                                isEventPrivacyStates = mutableStateOf(EventScreenMainContract.EventPrivacyStates.NO_SELECT),
                                playersGenderStates = mutableStateOf(
                                    EventScreenMainContract.PlayersGenderStates.NO_SELECT
                                ),
                                sportType = mutableStateOf(""),
                                priceStates = mutableStateOf(EventScreenMainContract.PriceStates.NO_SELECT),
                                needFormStates = mutableStateOf(
                                    EventScreenMainContract.NeedFormStates.NO_SELECT
                                ),
                                phoneNumberState = mutableStateOf(""),
                                eventDescription = mutableStateOf(""),
                                eventSummaryPrice = mutableStateOf(""),
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

    private fun loadEventData() {
        viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Loading
                )
            }
            currentState.currentEventId.value.let { currentEventId ->
                currentEventId?.let { eventId ->
                    getEventById(
                        eventId = eventId
                    )
                }
            }
        }
    }

    private fun getUserPhoneFromDataStore() {
        dataStoreCoroutineScope.launch(Dispatchers.IO) {
            val userPhoneWithoutPrefix =
                userPhoneManager.getUserPhone().firstOrNull()?.toString()?.removePrefix(
                    application.getString(
                        R.string.ua_phone_country_code
                    )
                )
            setState {
                copy(
                    phoneNumberState = mutableStateOf(
                        userPhoneWithoutPrefix ?: ""
                    )
                )
            }
            dataStoreCoroutineScope.cancel()
        }
    }

    private fun getEventById(eventId: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val userIdResult = userIdManager.getUserId().firstOrNull()
            getEventByIdUseCase.executeGetEventById(eventId).let {
                when (it) {
                    is GetEventByIdResultEntity.Success -> {
                        setState {
                            copy(
                                eventName = mutableStateOf(it.data.name),
                                isEventPrivate = mutableStateOf(it.data.privacy),
                                isFormNeed = mutableStateOf(it.data.need_form),
                                needBallSwitchButtonState = mutableStateOf(it.data.need_ball),
                                maxEventPlayersState = mutableStateOf(it.data.amount_members.toString()),
                                sportType = mutableStateOf(it.data.type),
                                eventDateAndTime = mutableStateOf(it.data.date_and_time),
                                eventDuration = mutableIntStateOf(it.data.duration),
                                eventPlaceName = mutableStateOf(it.data.place.place_name),
                                eventDescription = mutableStateOf(it.data.description),
                                eventGenders = mutableStateOf(it.data.gender),
                                eventAuthorFirstName = mutableStateOf(it.data.author.profile.name),
                                eventAuthorLastName = mutableStateOf(it.data.author.profile.last_name),
                                eventAuthorPhone = mutableStateOf(it.data.author.phone),
                                eventAuthorAvatar = mutableStateOf(
                                    it.data.author.profile.avatar_url ?: ""
                                ),
                                currentEventAuthorId = mutableIntStateOf(it.data.author.id),
                                eventPrice = mutableIntStateOf(it.data.price ?: 0),
                                isMyEvent = mutableStateOf(it.data.author.profile.id == userIdResult),
                                priceDescription = mutableStateOf(it.data.price_description),
                                eventLatLng = mutableStateOf(
                                    LatLng(
                                        it.data.place.lat,
                                        it.data.place.lon
                                    )
                                ),
                                invitedPlayersList = mutableStateOf(it.data.current_users.map { player -> player.profile }),
                                invitedFunsList = mutableStateOf(it.data.current_fans.map { observer -> observer.profile }),
                                state = EventScreenMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }
                        currentState.eventSummaryPrice.value =
                            checkNullIntPriceValue(currentState.eventPrice.value)
                        mapGenderOnEditScreen(
                            eventGenders = currentState.eventGenders.value,
                            context = application.applicationContext,
                            playersGenderStates = currentState.playersGenderStates,
                        )
                        mapPriceOnEditScreen(
                            price = currentState.eventPrice.value,
                            priceStates = currentState.priceStates,
                        )
                        mapPrivacyOnEditScreen(
                            isPrivacy = currentState.isEventPrivate.value,
                            privacyStates = currentState.isEventPrivacyStates,
                        )
                        mapFormStatesOnEditScreen(
                            isFormNeed = currentState.isFormNeed.value,
                            formNeedStates = currentState.needFormStates,
                        )
                        mapToDateOnEditScreen(
                            inputDateTime = currentState.eventDateAndTime.value,
                            outputDate = currentState.eventDateState,
                        )
                        mapToTimeOnEditScreen(
                            inputTime = currentState.eventDateAndTime.value,
                            outputTime = currentState.startEventTimeState,
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

    private fun joinToEvent(asWho: ParticipantRole) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (asWho) {
                is ParticipantRole.PLAYER -> {
                    joinToEventAsPlayerUseCase.executeJoinRequestAsPlayer(
                        eventId = currentState.currentEventId.value!!
                    ).let { result ->
                        when (result) {
                            is JoinToEventAsPlayerResultEntity.Success -> {
                                handleEvent(EventScreenMainContract.Event.SuccessfullyJoinToEvent)
                            }

                            is JoinToEventAsPlayerResultEntity.Error -> {
                                when (result.error.detail) {
                                    application.getString(R.string.event_time_expired) -> {
                                        currentState.isErrorMessageVisible.value = true
                                        currentState.errorMessageText.value =
                                            application.getString(R.string.event_time_expired_message)
                                    }
                                }

                            }
                        }

                    }
                }

                is ParticipantRole.FUN -> {

                }
            }
        }
    }

    private fun showToastMessage(toastType: EventToastType, durationMillis: Long) {
        when (toastType) {
            is EventToastType.SUCCESS -> {
                job = viewModelScope.launch(Dispatchers.Default) {
                    currentState.isSuccessMessageVisible.value = true
                    delay(durationMillis)
                    currentState.isSuccessMessageVisible.value = false
                }
            }

            is EventToastType.ERROR -> {
                job = viewModelScope.launch(Dispatchers.Default) {
                    currentState.isErrorMessageVisible.value = true
                    delay(durationMillis)
                    currentState.isErrorMessageVisible.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun setState(reduce: EventScreenMainContract.State.() -> EventScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}