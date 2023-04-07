package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _requestResetResult = MutableLiveData<EmailResetResultEntity>()
    val requestResetResult: LiveData<EmailResetResultEntity> = _requestResetResult

    private val _sendCodeResult = MutableLiveData<SendCodeResultEntity>()
    val sendCodeResult: LiveData<SendCodeResultEntity> = _sendCodeResult

    private val _requestResetCompleteResult = MutableLiveData<ResetCompleteResultEntity>()
    val requestResetCompleteResult: LiveData<ResetCompleteResultEntity> = _requestResetCompleteResult

    fun requestCompleteReset(newPassword: String) {
        viewModelScope.launch {
            val result = appRepository.changePassword(newPassword)
            _requestResetCompleteResult.value = result
        }
    }

    fun sendCode(code: String) {
        viewModelScope.launch {
            val result = appRepository.sendCode(code)
            _sendCodeResult.value = result
        }
    }

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
            is MainContract.Event.SendCodeClicked -> {
                setState {
                    copy(
                        bottomTabsVisible = false,
                        state = MainContract.ScreenViewState.Loading,
                    )
                }
                requestReset()
            }
        }
    }

   private fun requestReset() {

       job = viewModelScope.launch (Dispatchers.IO) {
             appRepository.sendCode(currentState.emailText).let {
                 when (it) {
                     is SendCodeResultEntity.Success -> {
                         it.data
                         setState { copy(
                             state =  MainContract.ScreenViewState.SuccessSendCode
                         ) }
                     }
                     is SendCodeResultEntity.Error -> _sideEffect.emit(MainContract.Effect.ShowToast("Erorr"))
                 }
             }
        }
    }
    private fun setState(reduce: MainContract.State.() -> MainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}