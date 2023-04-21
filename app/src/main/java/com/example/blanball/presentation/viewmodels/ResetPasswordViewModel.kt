package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.entity.SendCodeResultEntity
import com.example.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel
    @Inject constructor(internal val appRepository: AppRepository): ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = MainContract.State(
            state = MainContract.ScreenViewState.Idle
        ) //Этот код определяет свойство defaultState, которое является экземпляром MainContract.Stateсо значением по ScreenViewStateумолчанию Idle. Это состояние будет использоваться как начальное для ViewModel.

    val currentState: MainContract.State
        get() = uiState.value as MainContract.State // Этот код определяет свойство currentState, которое возвращает текущее состояние свойства ViewModel uiStateкак экземпляр MainContract.State. Это позволяет ViewModel легко получать доступ и изменять текущее состояние.

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<MainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is MainContract.Event.SendEmailResetRequestClicked -> {
                setState {
                    copy(
                        state = MainContract.ScreenViewState.Loading,
                    )
                }
                requestReset()
            }
            is MainContract.Event.SendCodeClicked -> {
                setState {
                    copy(
                        state = MainContract.ScreenViewState.Loading
                    )
                }
                sendCode()
            }
            is MainContract.Event.CompleteResetClicked -> {
                setState {
                    copy(
                        state = MainContract.ScreenViewState.Loading
                    )
                }
                requestCompleteReset()
            }
        }
    }

    private fun requestReset() {
        job = viewModelScope.launch(Dispatchers.IO) {
            appRepository.sendEmailPassReset(currentState.emailText.value).let {
                when (it) {
                    is EmailResetResultEntity.Success -> {
                        setState {
                            copy(
                                state = MainContract.ScreenViewState.SuccessResetRequest
                            )
                        }
                    }
                    is EmailResetResultEntity.Error -> MainContract.Effect.ShowToast("FAIL!=(")
                }
            }
        }
    }

    private fun sendCode() {
        val code: String = currentState.codeText.joinToString(separator = "") { it.value }
        job = viewModelScope.launch(Dispatchers.IO) {
            appRepository.sendCode(code).let {
                when (it) {
                    is SendCodeResultEntity.Success -> {
                        _sideEffect.emit(MainContract.Effect.ShowToast("Succes"))
                        setState {
                            copy(
                                state = MainContract.ScreenViewState.SuccessSendCodeRequest
                            )
                        }
                    }
                    is SendCodeResultEntity.Error -> _sideEffect.emit(
                        MainContract.Effect.ShowToast(
                            "Error"
                        )
                    )
                }
            }
        }
    }

    private fun requestCompleteReset() {
        job = viewModelScope.launch(Dispatchers.IO) {
            appRepository.changePassword(currentState.newPassText.value).let {
                when (it) {
                    is ResetCompleteResultEntity.Success -> {
                        _sideEffect.emit(MainContract.Effect.ShowToast("Succes"))
                        setState {
                            copy(
                                state = MainContract.ScreenViewState.SuccessCompleteResetRequest
                            )
                        }
                    }
                    is ResetCompleteResultEntity.Error -> _sideEffect.emit(
                        MainContract.Effect.ShowToast(
                            "Error"
                        )
                    )
                }
            }
        }
    }

    private fun setState(reduce: MainContract.State.() -> MainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    } //Reducer
}