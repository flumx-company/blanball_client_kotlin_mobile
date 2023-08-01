package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUserPlannedEventsByIdResultResponseEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseResultEntity

class PublicProfileMainContract {

    sealed class Event : UiEvent {
    }

    data class State(
        val state: ScreenViewState,
        var userFirstNameText: MutableState<String> = mutableStateOf(""),
        var userLastNameText: MutableState<String> = mutableStateOf(""),
        var userRoleText: MutableState<String?> = mutableStateOf(""),
        var userIsVerified: MutableState<Boolean> = mutableStateOf(false),
        var userEmail: MutableState<String> = mutableStateOf(""),
        var userPhoneNumberText: MutableState<String> = mutableStateOf(""),
        var aboutUserText: MutableState<String?> = mutableStateOf(""),
        var userPositionText: MutableState<String?> = mutableStateOf(""),
        var userWeightText: MutableState<Int?> = mutableStateOf(0),
        var userWorkingLegText: MutableState<String?> = mutableStateOf(""),
        var userHeightText: MutableState<Int?> = mutableStateOf(0),
        var userAvatar: MutableState<String?> = mutableStateOf(null),
        var rating: MutableState<Any?> = mutableStateOf(0),
        var reviewsCount: MutableState<Int> = mutableStateOf(0),
        var plannedEventsCount: MutableState<Int> = mutableStateOf(0),
        val reviewsList: MutableState<List<GetUserReviewsByIdResponseResultEntity>> = mutableStateOf(
            emptyList()
        ),
        val plannedEventsList: MutableState<List<GetUserPlannedEventsByIdResultResponseEntity>> = mutableStateOf(
            emptyList()
        ),
        var userIsConfirmed: MutableState<Boolean> = mutableStateOf(false),
        val isLoadingMoreReviews: Boolean = false,
        val allReviewsLoaded: Boolean = false,
        val isLoadingMoreEvents: Boolean = false,
        val allEventsLoaded: Boolean = false,
        val openInviteUserToInventModal: MutableState<Boolean> = mutableStateOf(false),
        val invitesList: MutableState<List<String>> = mutableStateOf(
            emptyList()
        ),
        val selectedInviteState: MutableState<String> = mutableStateOf(""),
        val addMessageState:  MutableState<String> = mutableStateOf(""),
        val userId: MutableState<Int> = mutableStateOf(0)
    ) : UiState

    sealed class ScreenViewState {
        object Idle: ScreenViewState()
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