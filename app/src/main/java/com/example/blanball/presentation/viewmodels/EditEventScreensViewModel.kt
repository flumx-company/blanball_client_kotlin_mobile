package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EditEventScreenMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.EventPrivacyStatesToBoolean
import com.example.blanball.utils.ext.NeedFormStatesToBoolean
import com.example.blanball.utils.ext.PlayersGenderStatesToString
import com.example.blanball.utils.ext.SportTypesStringsToEnglish
import com.example.blanball.utils.ext.calculateTimeDifferenceInMinutes
import com.example.blanball.utils.ext.formatToIso8601DateTime
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
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
class EditEventScreensViewModel
@Inject constructor(
    internal val editEventByIdUseCase: EditEventByIdUseCase,
    private val application: Application,
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = EditEventScreenMainContract.State(
            state = EditEventScreenMainContract.ScreenViewState.Idle,
        )

    val currentState: EditEventScreenMainContract.State
        get() = uiState.value as EditEventScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<EditEventScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<EditEventScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EditEventScreenMainContract.Event.EditEventClicked -> {
                setState {
                    copy(
                        state = EditEventScreenMainContract.ScreenViewState.Loading
                    )
                }
                requestEditEventById()
            }
        }
    }

    private fun requestEditEventById() {
        job = viewModelScope.launch(Dispatchers.IO) {
            editEventByIdUseCase.executeEditEventById(
                id = currentState.eventId.value,
                amount_members = currentState.maxEventPlayersState.value.toInt(),
                contact_number = currentState.phoneNumberState.value,
                date_and_time = formatToIso8601DateTime(
                    date = currentState.eventDateState.value,
                    time = currentState.startEventTimeState.value
                ),
                description = currentState.eventDescriptionState.value,
                duration = currentState.startEventTimeState.value.calculateTimeDifferenceInMinutes(
                    endTime = currentState.endEventTimeState.value
                ),
                gender = currentState.playersGenderStates.value.PlayersGenderStatesToString(context = application.applicationContext),
                hidden = false,
                name = currentState.eventName.value,
                need_ball = currentState.needBallSwitchButtonState.value,
                need_form = currentState.needFormStates.value.NeedFormStatesToBoolean(),
                place = "Todo", //TODO()
                lat = 90, //TODO()
                lon = 180, //TODO()
                price = 10, //TODO
                place_name = "Todo",
                price_description = "Todo", //TODO()
                type = currentState.sportType.value.SportTypesStringsToEnglish(context = application.applicationContext),
                forms = null, //TODO()
                privacy = currentState.isEventPrivacy.value.EventPrivacyStatesToBoolean(),
            ).let {
                when (it) {
                    is EditEventByIdResultEntity.Success -> {
                        setState {
                            copy(
                                state = EditEventScreenMainContract.ScreenViewState.SuccessRequest,
                                isSuccessEventCreation = mutableStateOf(true),
                                isErrorEventCreation = mutableStateOf(false),
                                eventName = mutableStateOf(""),
                                eventType = mutableStateOf(""),
                                playersGenderStates = mutableStateOf(EditEventScreenMainContract.PlayersGenderStates.NO_SELECT),
                                timeAndDateOfEvent = mutableStateOf(""),
                                placeOfEvent = mutableStateOf(""),
                                sportType = mutableStateOf(""),
                                entryStates = mutableStateOf(EditEventScreenMainContract.EntryStates.NO_SELECT),
                                contributingStates = mutableStateOf(EditEventScreenMainContract.СontributionsStates.NO_SELECT),
                                needFormStates = mutableStateOf(EditEventScreenMainContract.NeedFormStates.NO_SELECT),
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

                    is EditEventByIdResultEntity.Error -> {
                        setState {
                            copy(
                                state = EditEventScreenMainContract.ScreenViewState.ErrorRequest,
                                isErrorEventCreation = mutableStateOf(true),
                            )
                        }
                    }
                }
            }
        }
    }

    internal fun setState(reduce: EditEventScreenMainContract.State.() -> EditEventScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}