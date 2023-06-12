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
    val isMaleRadioButton: Boolean = false,
    val isFemaleRadioButton: Boolean = false,
    val isAllGenderRadioButton: Boolean = false,
    ) : UiState

sealed class ScreenViewState {
    object Loading : ScreenViewState()
    object LoadingSuccess: ScreenViewState()
    object LoadingError : ScreenViewState()
}

sealed class Effect : UiEffect {
    class ShowToast(val message: String) : Effect()
}

data class UiData(
    val progress: Float
)
}