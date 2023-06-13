package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUsersListResponseResultEntity
import com.example.domain.utils.Strings

class RatingUsersMainContract {

sealed class Event : UiEvent {
}

data class State(
    val state: RatingUsersMainContract.ScreenViewState,
    val usersList: MutableState<List<GetUsersListResponseResultEntity>> = mutableStateOf(
        emptyList()
    ),
    val isLoadingMoreUsers: Boolean = false,
    val allUsersLoaded: Boolean = false,
    val openFiltersDialog: MutableState<Boolean> = mutableStateOf(false),
    val genderSelectionState: MutableState<GenderSelectionState> = mutableStateOf(
        GenderSelectionState.ALL
    ),
    val ageSliderPosition: MutableState<ClosedFloatingPointRange<Float>> = mutableStateOf(6f..80f),
) : UiState

    enum class GenderSelectionState(val stringValue: String) {
        MALE(Strings.MALE),
        FEMALE(Strings.FEMALE),
        ALL(Strings.ALL)
    }

    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object LoadingSuccess : ScreenViewState()
        object LoadingError : ScreenViewState()
        object LoadingWithFilters : ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }

    data class UiData(
        val progress: Float
    )
}