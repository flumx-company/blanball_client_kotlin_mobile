package com.example.blanball.presentation.data

class RatingUsersMainContract {

sealed class Event : UiEvent {

}

data class State(
    val state: RatingUsersMainContract.ScreenViewState

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