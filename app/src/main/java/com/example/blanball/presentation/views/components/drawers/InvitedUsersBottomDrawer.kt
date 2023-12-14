package com.example.blanball.presentation.views.components.drawers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.InvitedUserCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitedUsersBottomDrawer(
    bottomDrawerState: SheetState,
    closeBottomDrawer: () -> Unit,
    state: UiState,
) {
    (state as? EventCreationScreenMainContract.State)?.let { currentState ->
        ModalBottomSheet(onDismissRequest = { closeBottomDrawer() }, containerColor = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Text(
                    text = stringResource(R.string.invited_users),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                    textAlign = TextAlign.Center,
                )
                if (currentState.selectedUserProfiles.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(12.dp))
                    for (user in currentState.selectedUserProfiles.value) {
                        Box(
                            modifier = Modifier.background(
                                color = Color.White,
                                shape = shapes.small
                            )
                        ) {
                            InvitedUserCard(
                                userAvatarUrl = user.profile.avatar_url ?: "",
                                userFirstName = user.profile.name,
                                userLastName = user.profile.last_name,
                                userPosition = user.profile.position ?: "",
                                onRemoveUserClicked = {
                                    currentState.selectedUserIds.value -= user.id
                                    currentState.selectedUserProfiles.value -= user
                                }
                            )
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}