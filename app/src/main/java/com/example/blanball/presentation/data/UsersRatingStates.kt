package com.example.blanball.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.entity.responses.GetUsersListResponseResultEntity
import com.example.domain.utils.Strings

class RatingUsersMainContract {

sealed class Event : UiEvent {
}

data class State(
    val state:ScreenViewState,
    val usersList: MutableState<List<GetUsersListResponseResultEntity>> = mutableStateOf(
        emptyList()
    ),
    val isLoadingMoreUsers: Boolean = false,
    val allUsersLoaded: Boolean = false,
    val userCounter: MutableState<Int> = mutableStateOf(0),
    val openFiltersDialog: MutableState<Boolean> = mutableStateOf(false),
    val genderSelectionState: MutableState<GenderSelectionState> = mutableStateOf(
        GenderSelectionState.ALL
    ),
    val ageSliderPosition: MutableState<ClosedFloatingPointRange<Float>> = mutableStateOf(6f..80f),
    val GamePositionSelectionState: MutableState<GamePositionSelectionState> = mutableStateOf(
        RatingUsersMainContract.GamePositionSelectionState.ALl),
    var positionSelectedItem: MutableState<String> = mutableStateOf("--"),
    val usersOrderingSelectionState: MutableState<UserOrderingSelectionState> = mutableStateOf(UserOrderingSelectionState.ALL),
    val orderingIconState: MutableState<Boolean> = mutableStateOf(false),
) : UiState

    enum class GenderSelectionState(val stringValue: String) {
        MALE(Strings.MALE),
        FEMALE(Strings.FEMALE),
        ALL(Strings.ALL)
    }

    enum class GamePositionSelectionState(val stringValue: String) {
        ALl(Strings.ALL),
        GK(Strings.GK),
        LB(Strings.LB),
        RB(Strings.RB),
        CB(Strings.CB),
        LWB(Strings.LWB),
        RWB(Strings.RWB),
        CDM(Strings.CDM),
        CM(Strings.CM),
        CAM(Strings.CAM),
        RM(Strings.RM),
        LM(Strings.LM),
        RW(Strings.RW),
        LW(Strings.LW),
        RF(Strings.RF),
        CF(Strings.CF),
        LF(Strings.LF),
        ST(Strings.ST),
    }

    enum class UserOrderingSelectionState (val stringValue: String) {
        ALL(Strings.ALL),
        FIRST_OLDER(Strings.FIRST_OLDER)
    }


    sealed class ScreenViewState {
        object Loading : ScreenViewState()
        object LoadingSuccess : ScreenViewState()
        object LoadingError : ScreenViewState()
        object LoadingWithFilters : ScreenViewState()
        object LoadingSuccessWithFilters : ScreenViewState()
        object LoadingWithNewOrdering: ScreenViewState()
        object LoadingSuccessWithNewOrdering: ScreenViewState()
    }

    sealed class Effect : UiEffect {
        class ShowToast(val message: String) : Effect()
    }

    data class UiData(
        val progress: Float
    )
}