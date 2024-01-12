package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.EmailVerificationMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.data.datastore.emailverificationmanager.EmailVerificationManager
import com.example.domain.entity.results.PostEmailVerifyCodeResultEntity
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResultEntity
import com.example.domain.usecases.interfaces.EmailVerificationUseCase
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
class EmailVerificationViewModel
@Inject constructor(
    private val emailVerificationUseCase: EmailVerificationUseCase,
    private val emailVerificationManager: EmailVerificationManager,
) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = EmailVerificationMainContract.State(
            state = EmailVerificationMainContract.ScreenViewState.Idle
        )

    val currentState: EmailVerificationMainContract.State
        get() = uiState.value as EmailVerificationMainContract.State

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<EmailVerificationMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<EmailVerificationMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is EmailVerificationMainContract.Event.SendCodeToUserEmailClicked -> {
                setState {
                    copy(
                        state = EmailVerificationMainContract.ScreenViewState.Loading,
                    )
                }
                sendCodeToUserEmail()
            }

            is EmailVerificationMainContract.Event.VerifyEmailClicked -> {
                setState {
                    copy(
                        state = EmailVerificationMainContract.ScreenViewState.Loading,
                    )
                }
                postEmailVerifyCode()
            }
        }
    }

    private fun sendCodeToUserEmail() {
        job = viewModelScope.launch(Dispatchers.IO) {
            emailVerificationUseCase.executeSendVerifyCodeToUserEmail(page = 1).let {
                when (it) {
                    is SendVerifyCodeToUserEmailResultEntity.Success -> {
                        setState {
                            copy(
                                state = EmailVerificationMainContract.ScreenViewState.SendCodeToUserEmailSuccess,
                                isSendCodeToUserEmailError = mutableStateOf(false),
                                isSendCodeToUserEmailSuccess = mutableStateOf(true),
                            )
                        }
                    }

                    is SendVerifyCodeToUserEmailResultEntity.Error -> setState {
                        copy(
                            isSendCodeToUserEmailError = mutableStateOf(
                                true
                            ),
                            state = EmailVerificationMainContract.ScreenViewState.SendCodeToUserEmailError
                        )
                    }
                }
            }
        }
    }


    private fun postEmailVerifyCode() {
        val code: String = currentState.codeText.joinToString(separator = "") { it.value }
        job = viewModelScope.launch(Dispatchers.IO) {
            emailVerificationUseCase.executePostEmailVerifyCode(code).let {
                when (it) {
                    is PostEmailVerifyCodeResultEntity.Success -> {
                        emailVerificationManager.saveIsEmailVerifiedState(isEmailVerified = true)
                        setState {
                            copy(
                                isEmailVerifyError = mutableStateOf(false),
                                isEmailVerifySuccess = mutableStateOf(true),
                                isEmailVerified = mutableStateOf(true),
                                state = EmailVerificationMainContract.ScreenViewState.EmailVerifySuccess,
                            )
                        }
                    }

                    is PostEmailVerifyCodeResultEntity.Error -> setState {
                        copy(
                            isEmailVerifyError = mutableStateOf(true),
                            state = EmailVerificationMainContract.ScreenViewState.EmailVerifyError
                        )
                    }
                }
            }
        }
    }

    fun setState(reduce: EmailVerificationMainContract.State.() -> EmailVerificationMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}