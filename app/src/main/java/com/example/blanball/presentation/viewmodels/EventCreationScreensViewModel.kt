package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
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
class EventCreationScreensViewModel
@Inject constructor(
    internal val creationNewEventUseCase: CreationAnEventUseCase,
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = EventCreationScreenMainContract.State(
            state = EventCreationScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: EventCreationScreenMainContract.State
        get() = uiState.value as EventCreationScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<EventCreationScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<EventCreationScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EventCreationScreenMainContract.Event.CreateNewEventClicked -> {
                setState {
                    copy(
                        state = EventCreationScreenMainContract.ScreenViewState.Loading
                    )
                }

            }
        }
    }

    private fun requestCreationNewEvent() {
        job = viewModelScope.launch(Dispatchers.IO) {
            creationNewEventUseCase.executeCreationAnEvent(
                amount_members = currentState.maxEventPlayersState.value.toInt(),
                contact_number = currentState.phoneNumberState.value,
                current_users = null,
                date_and_time = currentState.timeAndDateOfEvent.value,
                description = currentState.eventDescriptionState.value,
                duration = 10,
                forms = currentState.needFormStates.value,
                gender = currentState.playersGenderStates.value,
                hidden = currentState.,
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value,
                place = null, //TODO()
                price = currentState.priseSwitchButtonState.value,
                price_description = null, //TODO()
                privacy = currentState.,
                type = currentState.sportType.value,
            ).let {
                when (it) {
                    is CreationAnEventResultEntity.Success -> {
                        setState {
                            copy(
                                state = EventCreationScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                typeOfEvent = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(EventCreationScreenMainContract.PlayersGenderStates.NO_SELECT),
                                timeAndDateOfEvent = mutableStateOf(""),
                                placeOfEvent = mutableStateOf(""),
                                sportType = mutableStateOf(""),
                                entryStates = mutableStateOf(EventCreationScreenMainContract.EntryStates.NO_SELECT),
                                contributingStates = mutableStateOf(EventCreationScreenMainContract.СontributionsStates.NO_SELECT),
                                needFormStates = mutableStateOf(EventCreationScreenMainContract.NeedFormStates.NO_SELECT),
                                phoneNumberState = mutableStateOf(""),
                                eventDescriptionState = mutableStateOf(""),
                                startEventTimeState = mutableStateOf(""),
                                endEventTimeState = mutableStateOf(""),
                                maxEventPlayersState = mutableStateOf(""),
                                usersSearchState = mutableStateOf(""),
                                priseSwitchButtonState = mutableStateOf(false),
                                needBallSwitchButtonState = mutableStateOf(false),
                            )
                        }
                    }
                    is CreationAnEventResultEntity.Error -> {
                        setState {
                            copy(
                                state = EventCreationScreenMainContract.ScreenViewState.ErrorRequest,
                                isErrorEventCreation = mutableStateOf(true),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setState(reduce: EventCreationScreenMainContract.State.() -> EventCreationScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}