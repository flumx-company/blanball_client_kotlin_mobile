    package com.example.blanball.presentation.viewmodels

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.blanball.presentation.data.MainContract
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
                resetPasswordUseCase.executeSendEmailPassReset(currentState.resetEmailText.value).let {
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
                resetPasswordUseCase.executeSendCode(code).let {
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
                resetPasswordUseCase.executeChangePassword(currentState.newPassText.value).let {
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
        }
    }