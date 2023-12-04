package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.TechWorksScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.domain.entity.results.GetIsTechnicalWorkStatusResultEntity
import com.example.domain.usecases.interfaces.GetIsTechWorksUseCase
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
class TechWorksScreenViewModel
@Inject constructor(
    internal val getIsTechWorksUseCase: GetIsTechWorksUseCase,
) : ViewModel() {
    private var job: Job? = null

    val defaultState
        get() = TechWorksScreenMainContract.State(
            state = TechWorksScreenMainContract.ScreenViewState.Loading,
        )

    val currentState: TechWorksScreenMainContract.State
        get() = uiState.value as TechWorksScreenMainContract.State

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<TechWorksScreenMainContract.Effect> =
        MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<TechWorksScreenMainContract.Effect> = _sideEffect.asSharedFlow()

    internal fun handleScreenState(screenViewState: TechWorksScreenMainContract.ScreenViewState) {
        when (screenViewState) {
            is TechWorksScreenMainContract.ScreenViewState.Loading -> {
                getIsTechWorksStatus()
            }

            else -> {}
        }
    }

    private fun getIsTechWorksStatus() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getIsTechWorksUseCase.executeGetIsTechWorks().let {
                it.let {
                    when (it) {
                        is GetIsTechnicalWorkStatusResultEntity.Success -> {
                            val isTechWorkStatus = it.data.isMaintenance
                            setState {
                                copy(
                                    isTechWorksAvailable = mutableStateOf(isTechWorkStatus),
                                )
                            }
                        }

                        is GetIsTechnicalWorkStatusResultEntity.Error -> {

                        }
                    }
                }
            }

        }
    }

    internal fun setState(reduce: TechWorksScreenMainContract.State.() -> TechWorksScreenMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}