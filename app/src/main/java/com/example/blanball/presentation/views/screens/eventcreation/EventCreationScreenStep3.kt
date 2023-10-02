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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput

@Composable
fun     EventCreationScreenStep3(
    paddingValues: PaddingValues,
    state: UiState,
    isBottomDrawerOpen: MutableState<Boolean>,
    isInvitedUsersModalOpen: MutableState<Boolean>,
    bottomDrawerPreviewContent: @Composable () -> Unit,
    invitedUsersModalContent: @Composable () -> Unit,
    publishBtnClicked: () -> Unit,
    backBtnCLicked: () -> Unit,
) {
    val currentState: EventCreationScreenMainContract.State =
        (state as? EventCreationScreenMainContract.State) ?: EventCreationScreenMainContract.State(
            EventCreationScreenMainContract.ScreenViewState.Idle
        )
    val localFocusManager = LocalFocusManager.current
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
                    text = stringResource(R.string.do_you_need_your_own_uniform),
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
                            it.needFormStates.value =
                                EventCreationScreenMainContract.NeedFormStates.YES
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.needFormStates.value =
                                    EventCreationScreenMainContract.NeedFormStates.YES
                            },
                        state = it,
                        text = stringResource(R.string.yes),
                        selected = it.needFormStates.value ==
                                EventCreationScreenMainContract.NeedFormStates.YES,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            it.needFormStates.value =
                                EventCreationScreenMainContract.NeedFormStates.NO
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.needFormStates.value =
                                    EventCreationScreenMainContract.NeedFormStates.NO
                            },
                        state = it,
                        text = stringResource(R.string.no_have),
                        selected = it.needFormStates.value ==
                                EventCreationScreenMainContract.NeedFormStates.NO,
                        icon = null,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                  Row {
                      Text(
                          text = stringResource(R.string.will_there_be_a_prize_draw),
                          fontSize = 16.sp,
                          lineHeight = 24.sp,
                          style = typography.h3,
                          fontWeight = FontWeight(700),
                          color = primaryDark,
                      )
                      Spacer(modifier = Modifier.weight(1f))
                      SwitchButton(
                          state = it,
                          selected = it.priseSwitchButtonState.value,
                          onCheckedChange = {state.priseSwitchButtonState.value = it}
                      )
                  }
                Spacer(modifier = Modifier.size(16.dp))
                Row {
                    Text(
                        text = stringResource(R.string.need_ball),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        selected = it.needBallSwitchButtonState.value,
                        onCheckedChange = {state.needBallSwitchButtonState.value = it}
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.please_note_that_you),
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.my_contacts),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "+38 066 825 67 98", //TODO()
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = primaryDark,
                )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_change_data) ,
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.additional_information),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.add_your_comment),
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    textFieldModifier = Modifier
                        .fillMaxWidth()
                        .height(104.dp),
                    state = it,
                    isSingleLine = false,
                    value = state.eventDescriptionState.value,
                    onValueChange = { state.eventDescriptionState.value = it },
                    transformation = VisualTransformation.None,
                    labelResId = R.string.event_description,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )
                Spacer(modifier = Modifier.size(20.dp))
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
                        text = "3 / 3", //TODO()
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
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
                    repeat(3) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                NextAndPreviousButtonsHorizontal(
                    isEnabled = true,
                    nextBtnOnClick = { publishBtnClicked() },
                    prevBtnOnClick = { backBtnCLicked() },
                    nextBtnOnTextId = R.string.publish,
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
    if (currentState.state is EventCreationScreenMainContract.ScreenViewState.Loading) {
        Loader()
    }
}