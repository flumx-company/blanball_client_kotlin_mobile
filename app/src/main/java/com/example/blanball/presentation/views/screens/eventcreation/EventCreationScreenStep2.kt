package com.example.blanball.presentation.views.screens.eventcreation

import DottedLine
import OutlineRadioButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.InvitedUsersOfTheEventButton
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.buttons.PreviewOfTheEventPosterButton
import com.example.blanball.presentation.views.components.cards.UserCardOnEventCreation
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput

@Composable
fun EventCreationScreenStep2(
    paddingValues: PaddingValues,
    state: UiState,
    navigateToThirdStep: () -> Unit,
    isBottomDrawerOpen: MutableState<Boolean>,
    isInvitedUsersModalOpen: MutableState<Boolean>,
    bottomDrawerPreviewContent: @Composable () -> Unit,
    invitedUsersModalContent: @Composable () -> Unit,
    backBtnCLicked: () -> Unit
) {
    (state as? EventCreationScreenMainContract.State)?.let {
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Text(
                    text = stringResource(R.string.creation_event),
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = stringResource(R.string.privacy),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.do_you_want),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlineRadioButton(
                        onClick = {
                            it.isEventPrivacy.value =
                                EventCreationScreenMainContract.EventPrivacyStates.NO
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.isEventPrivacy.value =
                                    EventCreationScreenMainContract.EventPrivacyStates.NO
                            },
                        state = it,
                        text = stringResource(R.string.No_the_entrance_is_free),
                        selected = it.isEventPrivacy.value == EventCreationScreenMainContract.EventPrivacyStates.NO,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            it.isEventPrivacy.value =
                                EventCreationScreenMainContract.EventPrivacyStates.YES
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.isEventPrivacy.value =
                                    EventCreationScreenMainContract.EventPrivacyStates.YES
                            },
                        state = it,
                        text = stringResource(R.string.yes_the_event_is_closed),
                        selected = it.isEventPrivacy.value ==
                            EventCreationScreenMainContract.EventPrivacyStates.YES,
                        icon = null,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.does_participation_in_the_event),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlineRadioButton(
                        onClick = {
                            it.entryStates.value =
                                EventCreationScreenMainContract.EntryStates.FREE_ENTRY
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.entryStates.value =
                                    EventCreationScreenMainContract.EntryStates.FREE_ENTRY
                            },
                        state = it,
                        text = stringResource(id = R.string.free),
                        selected = it.entryStates.value == EventCreationScreenMainContract.EntryStates.FREE_ENTRY,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            it.entryStates.value =
                                EventCreationScreenMainContract.EntryStates.CLOSE_ENTRY
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.entryStates.value =
                                    EventCreationScreenMainContract.EntryStates.CLOSE_ENTRY
                            },
                        state = it,
                        text = stringResource(R.string.paid),
                        selected = it.entryStates.value ==
                            EventCreationScreenMainContract.EntryStates.CLOSE_ENTRY,
                        icon = null,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.invitation_of_participants),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.specify_the_number),
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(20.dp))
                DefaultTextInput(
                    labelResId = R.string.max_50,
                    state = it,
                    value = it.maxEventPlayersState.value,
                    onValueChange = {state.maxEventPlayersState.value = it},
                    transformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_peoples),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.users_search,
                    state = it,
                    value = it.usersSearchState.value,
                    onValueChange = {state.usersSearchState.value = it},
                    transformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    },
                    leadingIcon = {
                        Icon(
                        painter = painterResource(id = R.drawable.ic_add_user),
                        contentDescription = null,
                        tint = primaryDark,
                    )
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                repeat(5) {
                UserCardOnEventCreation(
                        userAvatarUrl = "http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg",
                        userFirstName = "Жук",
                        userLastName = "Женя",
                        )
                }
                DottedLine(color = defaultLightGray)
                Spacer(modifier = Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.progress),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = avatarGrey,
                    )
                    Text(
                        text = stringResource(R.string._2_3), //TODO()
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                }
                Row(
                    Modifier.padding(top = 20.dp)
                ) {
                    repeat(2) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.empty_stepline),
                        contentDescription = null,
                        Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                NextAndPreviousButtonsHorizontal (
                    isEnabled = true,
                    nextBtnOnClick = { navigateToThirdStep() },
                    prevBtnOnClick = { backBtnCLicked() },
                    nextBtnOnTextId = R.string.next,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterButton { isBottomDrawerOpen.value = true }
                Spacer(modifier = Modifier.size(16.dp))
                InvitedUsersOfTheEventButton { isInvitedUsersModalOpen.value = true }
                when {
                    isBottomDrawerOpen.value -> bottomDrawerPreviewContent()
                    isInvitedUsersModalOpen.value -> invitedUsersModalContent()
                }
            }
        }
    }
}