package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.usecases.interfaces.RegistrationUseCase
import com.example.domain.utils.Strings
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
class   RegistrationViewModel @Inject constructor(internal val registrationUseCase: RegistrationUseCase) :
    ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = StartScreensMainContract.State(
            state = StartScreensMainContract.ScreenViewState.Idle
        )

    val currentState: StartScreensMainContract.State
        get() = uiState.value as StartScreensMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<StartScreensMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<StartScreensMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is StartScreensMainContract.Event.RegistrationClicked -> {
                setState {
                    copy(
                        state = StartScreensMainContract.ScreenViewState.Loading
                    )
                }
                requestRegistration()
            }
        }
    }

    private fun requestRegistration() {
        job = viewModelScope.launch(Dispatchers.IO) {
            registrationUseCase.executeRegistration(
                email = currentState.registrationEmailText.value,
                phone = "+380${currentState.phoneNumberText.value}",
                password = currentState.registrationPassText.value,
                re_password = currentState.registrationPassTextRemember.value,
                name = currentState.firstNameText.value,
                lastName = currentState.lastNameText.value,
                gender = when (currentState.genderIsMale.value) {
                    true -> Strings.MAN
                    else -> Strings.WOMAN
                },
            ).let {
                when (it) {
                    is RegistrationResultEntity.Success -> {
                        setState {
                            copy(
                                state = StartScreensMainContract.ScreenViewState.SuccessRegistration,
                                isErrorRegistrationNewPass = mutableStateOf(false),
                            )
                        }
                    }
                    is RegistrationResultEntity.Error -> {
                        setState {
                            copy(
                                state = StartScreensMainContract.ScreenViewState.ErrorRegistration,
                                isErrorRegistrationNewPass = mutableStateOf(true),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setState(reduce: StartScreensMainContract.State.() -> StartScreensMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}