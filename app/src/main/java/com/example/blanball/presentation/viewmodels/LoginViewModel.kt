package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.usecases.interfaces.UserLoginUseCase
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
class LoginViewModel
@Inject constructor(
    internal val loginUseCase: UserLoginUseCase,
    val rememberMeManager: RememberMeManager
    ) : ViewModel() {

    private var job: Job? = null

    val defaultState
        get() = StartScreensMainContract.State(
            state = StartScreensMainContract.ScreenViewState.Idle,
        )

    val currentState: StartScreensMainContract.State
        get() = uiState.value as StartScreensMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<StartScreensMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<StartScreensMainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is StartScreensMainContract.Event.LoginClicked -> {
                setState {
                    copy(
                        state = StartScreensMainContract.ScreenViewState.Loading,
                    )
                }
                login()
            }
        }
    }

    private fun login() {
        job = viewModelScope.launch (Dispatchers.IO){
           loginUseCase.executeUserLogin(currentState.loginEmailText.value, currentState.loginPasswordText.value).let {
               when(it) {
                   is LoginResultEntity.Success -> {
                       rememberMeManager.saveRememberMeFlag(currentState.rememberMeCheckbox.value)
                       setState { copy(
                           isErrorLoginRequest = mutableStateOf(false),
                           isSuccessLoginRequest = mutableStateOf(true),
                           state = StartScreensMainContract.ScreenViewState.SuccessLogin,
                           loginEmailText = mutableStateOf(""),
                           loginPasswordText = mutableStateOf(""),
                       ) }
                   }
                   is LoginResultEntity.Error -> setState {
                       copy(isErrorLoginRequest =  mutableStateOf(true),
                       state = StartScreensMainContract.ScreenViewState.LoginError) }
               }
           }
        }
    }

    private fun setState(reduce: StartScreensMainContract.State.() -> StartScreensMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}