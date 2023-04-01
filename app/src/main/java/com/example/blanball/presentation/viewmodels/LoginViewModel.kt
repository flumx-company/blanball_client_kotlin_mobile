package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.LoginResultEntity
import com.example.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject constructor(internal val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResultEntity>()
    val loginResult: LiveData<LoginResultEntity> = _loginResult
    private var job: Job? = null

    val defaultState
        get() = MainContract.State(
            state = MainContract.ScreenViewState.Idle,
        )

    // Get Current State
    val currentState: MainContract.State
        get() = uiState.value as MainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect : MutableSharedFlow<MainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<MainContract.Effect> = _sideEffect.asSharedFlow()

    fun handleEvent(event: UiEvent) {
        when (event) {
            is MainContract.Event.LoginClicked -> {
                setState {
                    copy(
                        bottomTabsVisible = false,
                        state = MainContract.ScreenViewState.Loading,
                    )
                }
                login()
            }

//            is MainContract.Event.OnNavigationChanged -> {
//                when(event.n){
//                    is NavScreen.HomeTab, NavScreen.SearchTab ->{
//                        setState { copy( bottomTabsVisible = true ) }
//                    }
//                    else -> {
//                        setState { copy(bottomTabsVisible = false) }
//                    }
//                }
//            }
        }
    }

    private fun login() {
        job = viewModelScope.launch (Dispatchers.IO){
            loginRepository.login(currentState.loginText, currentState.passwordText).let {
                when(it) {
                    is LoginResultEntity.Success -> {
                        it.data// TODO do something with data
                        setState { copy(
                            state = MainContract.ScreenViewState.SuccessLogin
                        ) }
                    }
                    is LoginResultEntity.Error -> _sideEffect.emit(MainContract.Effect.ShowToast(it.error.detail))
                }

            }
        }
    }

    private fun setState(reduce: MainContract.State.() -> MainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}