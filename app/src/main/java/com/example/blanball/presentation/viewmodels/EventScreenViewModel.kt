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
import com.example.blanball.utils.errorshandler.DetailMessageHandler
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
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityForms
import com.example.domain.entity.results.AcceptOrDiscardParticipationResult
import com.example.domain.entity.results.CreationAnEventResult
import com.example.domain.entity.results.EditEventByIdResult
import com.example.domain.entity.results.GetEventByIdResult
import com.example.domain.entity.results.GetPrivateEventRequestListResult
import com.example.domain.entity.results.GetRelevantUserSearchListResult
import com.example.domain.entity.results.JoinToEventAsFanResult
import com.example.domain.entity.results.JoinToEventAsPlayerResult
import com.example.domain.entity.results.LeaveTheEventAsFanResult
import com.example.domain.entity.results.LeaveTheEventAsPlayerResult
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
import com.example.domain.usecases.interfaces.JoinToEventAsFunUseCase
import com.example.domain.usecases.interfaces.JoinToEventAsPlayerUseCase
import com.example.domain.usecases.interfaces.LeaveTheEventUseCase
import com.example.domain.usecases.interfaces.UserRequestingForPrivateEventUseCase
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
    private val joinToEventAsFanUseCase: JoinToEventAsFunUseCase,
    private val joinToEventAsPlayerUseCase: JoinToEventAsPlayerUseCase,
    private val leaveTheEventUseCase: LeaveTheEventUseCase,
    private val userRequestingUseCase: UserRequestingForPrivateEventUseCase,
    private val detailMessageHandler: DetailMessageHandler,
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

    sealed class UserRole {
        object PLAYER : UserRole()
        object FAN : UserRole()
    }

    sealed class ToastMessageType {
        object SUCCESS : ToastMessageType()
        object ERROR : ToastMessageType()
    }

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EventScreenMainContract.Event.LoadEventData -> {
//                getEventById()
                getListOfRequests()
            }

            is EventScreenMainContract.Event.GetUserPhone -> {
                getUserPhoneFromDataStore()
            }

            is EventScreenMainContract.Event.CleanStates -> {
                cleanStates()
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
                setState {
                    copy(
                        state = EventScreenMainContract.ScreenViewState.Loading
                    )
                }
                joinToEvent(asWho = UserRole.PLAYER)
            }


            is EventScreenMainContract.Event.JoinToEventAsFun -> {
                setState {
                    copy(
                        state = EventScreenMainContract.ScreenViewState.Loading
                    )
                }
                joinToEvent(asWho = UserRole.FAN)
            }

            is EventScreenMainContract.Event.LeaveTheEvent -> {
                setState {
                    copy(
                        state = EventScreenMainContract.ScreenViewState.Loading
                    )
                }
                leaveTheEvent()
            }

            is EventScreenMainContract.Event.AcceptEventRequest -> {
                acceptEventRequest(isAccept = true)
            }

            is EventScreenMainContract.Event.DiscardEventRequest -> {
                acceptEventRequest(isAccept = false)
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
                    is GetRelevantUserSearchListResult.Success -> {
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

                    is GetRelevantUserSearchListResult.Error -> {
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
                    is EditEventByIdResult.Success -> {
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

                    is EditEventByIdResult.Error -> {
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
                    is CreationAnEventResult.Success -> {
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
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

                    is CreationAnEventResult.Error -> {
                        showToastMessage(
                            message = detailMessageHandler.handleErrorMessage(it.error.detail),
                            type = ToastMessageType.ERROR,
                        )
                        setState {
                            copy(
                                state = EventScreenMainContract.ScreenViewState.Idle,
                            )
                        }
                    }
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

    private fun getEventById() {
        job = viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Loading
                )
            }
            delay(300)
            val userIdResult = userIdManager.getUserId().firstOrNull()
            getEventByIdUseCase.executeGetEventById(
                currentState.currentEventId.value!!
            ).let {
                when (it) {
                    is GetEventByIdResult.Success -> {
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
                                isParticipantAsPlayer = mutableStateOf(
                                    it.data.current_users.any { player -> player.id == userIdResult }
                                ),
                                isParticipantAsFan = mutableStateOf(
                                    it.data.current_fans.any { fan -> fan.id == userIdResult }
                                ),
                                priceDescription = mutableStateOf(it.data.price_description),
                                eventLatLng = mutableStateOf(
                                    LatLng(
                                        it.data.place.lat,
                                        it.data.place.lon
                                    )
                                ),
                                invitedPlayersList = mutableStateOf(it.data.current_users),
                                invitedFansList = mutableStateOf(it.data.current_fans),
                                state = EventScreenMainContract.ScreenViewState.SuccessRequest,
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

                    is GetEventByIdResult.Error -> {
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

    private fun joinToEvent(asWho: UserRole) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when (asWho) {
                is UserRole.PLAYER -> {
                    joinToEventAsPlayerUseCase.executeJoinRequestAsPlayer(
                        eventId = currentState.currentEventId.value!!
                    ).let { result ->
                        when (result) {
                            is JoinToEventAsPlayerResult.Success -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.success.data.success
                                    ),
                                    type = ToastMessageType.SUCCESS,
                                )
                                handleEvent(EventScreenMainContract.Event.LoadEventData)
                            }

                            is JoinToEventAsPlayerResult.Error -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(message = result.error.detail),
                                    type = ToastMessageType.ERROR
                                )
                            }
                        }
                    }
                }

                is UserRole.FAN -> {
                    joinToEventAsFanUseCase.executeJoinRequestAsFun(
                        eventId = currentState.currentEventId.value!!
                    ).let { result ->
                        when (result) {
                            is JoinToEventAsFanResult.Success -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.success.data.success
                                    ),
                                    type = ToastMessageType.SUCCESS,
                                )
                                handleEvent(EventScreenMainContract.Event.LoadEventData)
                            }

                            is JoinToEventAsFanResult.Error -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(message = result.error.detail),
                                    type = ToastMessageType.ERROR
                                )
                            }
                        }
                    }
                }
            }
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Idle,
                )
            }
        }
    }


    private fun leaveTheEvent() {
        job = viewModelScope.launch(Dispatchers.IO) {
            when {
                currentState.isParticipantAsPlayer.value -> {
                    leaveTheEventUseCase.leaveTheEventRequestAsPlayer(
                        eventId = currentState.currentEventId.value!!
                    ).let { result ->
                        when (result) {
                            is LeaveTheEventAsPlayerResult.Success -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.success.data.success
                                    ),
                                    type = ToastMessageType.SUCCESS
                                )
                                handleEvent(EventScreenMainContract.Event.LoadEventData)
                            }

                            is LeaveTheEventAsPlayerResult.Error -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.error.detail
                                    ),
                                    type = ToastMessageType.ERROR
                                )
                            }
                        }
                    }
                }

                currentState.isParticipantAsFan.value -> {
                    leaveTheEventUseCase.leaveTheEventRequestAsFan(
                        eventId = currentState.currentEventId.value!!
                    ).let { result ->
                        when (result) {
                            is LeaveTheEventAsFanResult.Success -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.success.data.success
                                    ),
                                    type = ToastMessageType.SUCCESS
                                )
                                handleEvent(EventScreenMainContract.Event.LoadEventData)
                            }

                            is LeaveTheEventAsFanResult.Error -> {
                                showToastMessage(
                                    message = detailMessageHandler.handleErrorMessage(
                                        message = result.error.detail
                                    ),
                                    type = ToastMessageType.ERROR
                                )
                            }
                        }
                    }
                }
            }
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Idle,
                )
            }
        }
    }

    private fun getListOfRequests() {
        job = viewModelScope.launch(Dispatchers.IO) {
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Loading
                )
            }
            userRequestingUseCase.executeGetPrivateEventRequestList(
                eventId = currentState.currentEventId.value!!
            ).let { result ->
                when (result) {
                    is GetPrivateEventRequestListResult.Success -> {
                        setState {
                            copy(
                                userRequestsList = mutableStateOf(result.data.results),
                            )
                        }
                    }

                    is GetPrivateEventRequestListResult.Error -> {
                        showToastMessage(
                            message = detailMessageHandler.handleErrorMessage(
                                message = result.error.detail
                            ),
                            type = ToastMessageType.ERROR
                        )
                    }
                }
            }
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Idle,
                )
            }
        }
    }

    private fun showToastMessage(
        message: String,
        type: ToastMessageType,
        durationMillis: Long = 3000,
    ) {
        job = viewModelScope.launch(Dispatchers.Default) {
            when (type) {
                is ToastMessageType.SUCCESS -> {
                    currentState.successMessageText.value = message
                    currentState.isSuccessMessageVisible.value = true
                    delay(durationMillis)
                    currentState.isSuccessMessageVisible.value = false
                }

                is ToastMessageType.ERROR -> {
                    currentState.errorMessageText.value = message
                    currentState.isErrorMessageVisible.value = true
                    delay(durationMillis)
                    currentState.isErrorMessageVisible.value = false
                }
            }
        }
    }

    private fun acceptEventRequest(isAccept: Boolean) {
        job = viewModelScope.launch(Dispatchers.IO) {
            userRequestingUseCase.executeAcceptingDiscardingEventRequest(
                isAcceptEventRequest = isAccept,
                ids = listOf(currentState.currentEventRequestUserId.value)
            ).let { result ->
                when (result) {
                    is AcceptOrDiscardParticipationResult.Success -> {
                        //TODO()
                        handleEvent(EventScreenMainContract.Event.LoadEventData)
                    }

                    is AcceptOrDiscardParticipationResult.Error -> {
                        showToastMessage(
                            message = detailMessageHandler.handleErrorMessage(
                                message = result.error.detail
                            ),
                            type = ToastMessageType.ERROR
                        )
                    }
                }
            }
            setState {
                copy(
                    state = EventScreenMainContract.ScreenViewState.Idle
                )
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
                    invitedFansList = mutableStateOf(
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
                    isUserHasBeenJoinedToEvent = mutableStateOf(false),
                    errorMessageText = mutableStateOf(""),
                    isErrorMessageVisible = mutableStateOf(false),
                    currentUserRole = mutableStateOf(""),
                    isParticipantAsPlayer = mutableStateOf(false),
                    isParticipantAsFan = mutableStateOf(false),
                )
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