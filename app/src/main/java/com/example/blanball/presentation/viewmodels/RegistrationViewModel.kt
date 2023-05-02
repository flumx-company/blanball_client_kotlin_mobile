package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.MainContract
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
class RegistrationViewModel @Inject constructor(internal val registrationUseCase: RegistrationUseCase) :
    ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = MainContract.State(
            state = MainContract.ScreenViewState.Idle
        )

    val currentState: MainContract.State
        get() = uiState.value as MainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<MainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MainContract.Effect> = _sideEffect.asSharedFlow()



    fun handleEvent(event: UiEvent) {
        when (event) {
            is MainContract.Event.RegistrationClicked -> {
                setState {
                    copy(
                        state = MainContract.ScreenViewState.Loading
                    )
                }
                requestRegistration()
            }
        }
    }

    private fun requestRegistration() {
         val fullName = currentState.nameText.value
         val parts = fullName.split(" ")

        job = viewModelScope.launch(Dispatchers.IO) {
            registrationUseCase.executeRegistration(
                email = currentState.registerEmailText.value,
                phone = "+${currentState.phoneNumberText.value}",
                password = currentState.resetPassText.value,
                re_password = currentState.resetPassTextRemember.value,
                name = parts[0],
                lastName = parts[1],
                gender = when (currentState.genderIsMale.value) {
                    true -> Strings.MAN
                    else -> Strings.WOMAN
                },
            ).let {
                when (it) {
                    is RegistrationResultEntity.Success -> {
                        setState {
                            copy(
                                state = MainContract.ScreenViewState.SuccessResetRequest
                            )
                        }
                    }
                    is RegistrationResultEntity.Error -> MainContract.Effect.ShowToast("FAIL!=(")
                }
            }
        }
    }

    private fun setState(reduce: MainContract.State.() -> MainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}