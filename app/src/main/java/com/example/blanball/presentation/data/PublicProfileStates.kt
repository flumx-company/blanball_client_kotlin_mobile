package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUserReviewsByIdResponseResultEntity

class PublicProfileMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        var userFirstNameText: MutableState<String> = mutableStateOf("----"),
        var userLastNameText: MutableState<String> = mutableStateOf("----"),
        var userRoleText: MutableState<String> = mutableStateOf("----"),
        var userIsVerified: MutableState<Boolean> = mutableStateOf(false),
        var userEmail: MutableState<String> = mutableStateOf("----"),
        var userPhoneNumberText: MutableState<String> = mutableStateOf("----"),
        var aboutUserText: MutableState<String> = mutableStateOf("----"),
        var userPositionText: MutableState<String> = mutableStateOf("----"),
        var userWeightText: MutableState<Int> = mutableStateOf(0),
        var userWorkingLegText: MutableState<String> = mutableStateOf("----"),
        var userHeightText: MutableState<Int> = mutableStateOf(0),
        var userAvatar: MutableState<String> = mutableStateOf("----"),
        var rating: MutableState<Any?> = mutableStateOf(0),
        var gradesCount: MutableState<Int> = mutableStateOf(0),
        val resultList: MutableState<List<GetUserReviewsByIdResponseResultEntity>> = mutableStateOf(emptyList()),
        val totalCount: MutableState<Int> = mutableStateOf(0),
        val remainingReviewsCount: MutableState<Int> = mutableStateOf(0)
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