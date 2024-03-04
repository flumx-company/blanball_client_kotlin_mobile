package com.example.blanball.presentation.views.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.HomeScreenEventCardHorizontalList
import com.example.blanball.presentation.views.components.modals.NewEventSuccessfullyCreatedModal
import com.example.blanball.presentation.views.components.modals.SuccessEditEventModal
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onNavigateToEvent: (eventId: Int) -> Unit,
    state: UiState,
    onLoadMoreUsers: () -> Unit,
    userFirstName: String,
) {
    (state as? FutureEventsMainContract.State)?.let { currentState ->
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 20.dp)
            ) {
                    Row {
                        Text(
                            text = stringResource(R.string.hi),
                            fontSize = 20.sp,
                            lineHeight = 32.sp,
                            style = typography.h3,
                            fontWeight = FontWeight(700),
                            color = secondaryNavy,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = userFirstName,
                            fontSize = 20.sp,
                            lineHeight = 32.sp,
                            style = typography.h3,
                            fontWeight = FontWeight(700),
                            color = primaryDark,
                        )
                    }
                    Spacer(Modifier.size(10.dp))
                    Text(
                        text = stringResource(R.string.what_activities_are_we_planning_today),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = secondaryNavy,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    HomeScreenEventCardHorizontalList(
                        clickToEventCardCallback = { eventId -> onNavigateToEvent(eventId) },
                        onLoadMoreUsers = onLoadMoreUsers,
                        state = state,
                    )
            }
        LaunchedEffect(Unit) {
            when {
                currentState.isShowEventSuccessCreatedModal.value -> {
                    delay(2000)
                    currentState.isShowEventSuccessCreatedModal.value = false
                }
                currentState.isShowEventSuccessEditModal.value -> {
                    delay(2000)
                    currentState.isShowEventSuccessEditModal.value = false
                }
            }
        }
        NewEventSuccessfullyCreatedModal(isModalVisible = currentState.isShowEventSuccessCreatedModal.value)
        SuccessEditEventModal(isModalVisible = currentState.isShowEventSuccessEditModal.value)
        }
    }
}