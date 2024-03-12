    package com.example.blanball.presentation.viewmodels

    import androidx.compose.runtime.mutableStateOf
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.blanball.presentation.data.StartScreensMainContract
    import com.example.blanball.presentation.data.UiEvent
    import com.example.blanball.presentation.data.UiState
    import com.example.domain.entity.results.EmailResetResultEntity
    import com.example.domain.entity.results.ResetCompleteResultEntity
    import com.example.domain.entity.results.SendCodeResultEntity
    import com.example.domain.usecases.interfaces.ResetPasswordUseCase
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
    class ResetPasswordViewModel
    @Inject constructor(
        internal val resetPasswordUseCase: ResetPasswordUseCase,
    ) : ViewModel() {

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
                is StartScreensMainContract.Event.SendEmailResetRequestClicked -> {
                    setState {
                        copy(
                            state = StartScreensMainContract.ScreenViewState.Loading,
                        )
                    }
                    requestReset()
                }
                is StartScreensMainContract.Event.SendCodeClicked -> {
                    setState {
                        copy(
                            state = StartScreensMainContract.ScreenViewState.Loading
                        )
                    }
                    sendCode()
                }
                is StartScreensMainContract.Event.CompleteResetClicked -> {
                    setState {
                        copy(
                            state = StartScreensMainContract.ScreenViewState.Loading
                        )
                    }
                    requestCompleteReset()
                }
            }
        }

        private fun requestReset() {
            job = viewModelScope.launch(Dispatchers.IO) {
                resetPasswordUseCase.executeSendEmailPassReset(currentState.resetEmailText.value).let {
                    when (it) {
                        is EmailResetResultEntity.Success -> {
                            setState {
                                copy(
                                    state = StartScreensMainContract.ScreenViewState.SuccessResetRequest,
                                    isErrorResetEmailState = mutableStateOf(false),
                                    isSuccessResetRequest = mutableStateOf(true),
                                )
                            }
                        }

                        is EmailResetResultEntity.Error -> setState {
                            copy(
                                isErrorResetEmailState = mutableStateOf(
                                    true
                                ),
                                state = StartScreensMainContract.ScreenViewState.ErrorResetRequest
                            )
                        }
                    }
                }
            }
        }


        private fun sendCode() {
            val code: String = currentState.codeText.joinToString(separator = "") { it.value }
            job = viewModelScope.launch(Dispatchers.IO) {
                resetPasswordUseCase.executeSendCode(code).let {
                    when (it) {
                        is SendCodeResultEntity.Success -> {
                            setState {
                                copy(
                                    state = StartScreensMainContract.ScreenViewState.SuccessSendCodeRequest,
                                    isErrorSendCodeState = mutableStateOf(false),
                                    isSuccessSendCodeState = mutableStateOf(true),
                                )
                            }
                        }

                        is SendCodeResultEntity.Error -> setState {
                            copy(
                                isErrorSendCodeState = mutableStateOf(
                                    true
                                ),
                                state = StartScreensMainContract.ScreenViewState.ErrorSendCodeRequest
                            )
                        }
                    }
                }
            }
        }

        private fun requestCompleteReset() {
            job = viewModelScope.launch(Dispatchers.IO) {
                resetPasswordUseCase.executeChangePassword(currentState.newPassText.value).let {
                    when (it) {
                        is ResetCompleteResultEntity.Success -> {
                            setState {
                                copy(
                                    state = StartScreensMainContract.ScreenViewState.SuccessCompleteResetRequest,
                                    isErrorCompleteResetState = mutableStateOf(false),
                                    isSuccessCompleteResetState = mutableStateOf(true),
                                    resetEmailText = mutableStateOf(""),
                                    codeText = List(5){ mutableStateOf("") },
                                    newPassText = mutableStateOf(""),
                                    repeatNewPassText = mutableStateOf(""),
                                )
                            }
                        }
                        is ResetCompleteResultEntity.Error ->
                            setState { copy(
                                state = StartScreensMainContract.ScreenViewState.ErrorCompleteResetRequest,
                                isErrorCompleteResetState = mutableStateOf(true),
                            ) }
                    }
                }
            }
        }

        fun setState(reduce: StartScreensMainContract.State.() -> StartScreensMainContract.State) {
            val newState = currentState.reduce()
            _uiState.value = newState
        }

        override fun onCleared() {
            super.onCleared()
            job?.cancel()
        }
    }