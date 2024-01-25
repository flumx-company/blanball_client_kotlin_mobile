package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.data.datastore.useridmanager.UserIdManager
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
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