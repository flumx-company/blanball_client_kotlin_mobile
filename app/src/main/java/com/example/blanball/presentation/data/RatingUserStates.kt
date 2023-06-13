package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUsersListResponseResultEntity

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
    val isMaleSelected:  MutableState<Boolean> = mutableStateOf(false),
    val isFemaleSelected:  MutableState<Boolean> = mutableStateOf(false),
    val isAllGenderSelected:  MutableState<Boolean> = mutableStateOf(false),
    val ageSliderPosition: MutableState<ClosedFloatingPointRange<Float>> = mutableStateOf(6f..80f),
    ) : UiState

sealed class ScreenViewState {
    object Loading : ScreenViewState()
    object LoadingSuccess: ScreenViewState()
    object LoadingError : ScreenViewState()
    object LoadingWithFilters: ScreenViewState()
}

sealed class Effect : UiEffect {
    class ShowToast(val message: String) : Effect()
}

data class UiData(
    val progress: Float
)
}