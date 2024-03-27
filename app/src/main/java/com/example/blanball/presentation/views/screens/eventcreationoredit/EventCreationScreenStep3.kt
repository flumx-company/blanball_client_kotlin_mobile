package com.example.blanball.presentation.views.screens.eventcreationoredit

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.InvitedUsersOfTheEventButton
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.buttons.PreviewOfTheEventPosterButton
import com.example.blanball.presentation.views.components.cards.ErrorMessageCard
import com.example.blanball.presentation.views.components.cards.SuccessMessageCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.PhoneNumberInput
import com.example.blanball.utils.ext.isInvalidValidPhoneNumber
import com.example.domain.utils.Integers

@Composable
fun EventEditOrCreationScreenStep3(
    paddingValues: PaddingValues,
    state: UiState,
    bottomDrawerPreviewContent: @Composable () -> Unit,
    invitedUsersModalContent: @Composable () -> Unit,
    publishBtnClicked: () -> Unit,
    backBtnCLicked: () -> Unit,
    isEditOrCreation: EventScreenMainContract.EditOrCreationState,
) {
    val localFocusManager = LocalFocusManager.current

    (state as? EventScreenMainContract.State)?.let { currentState ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = if (
                        isEditOrCreation == EventScreenMainContract.EditOrCreationState.CREATION
                    ) stringResource(R.string.creation_event) else stringResource(id = R.string.edit_event),
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
                            currentState.needFormStates.value =
                                EventScreenMainContract.NeedFormStates.YES
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.needFormStates.value =
                                    EventScreenMainContract.NeedFormStates.YES
                            },
                        state = currentState,
                        text = stringResource(R.string.yes),
                        selected = currentState.needFormStates.value ==
                                EventScreenMainContract.NeedFormStates.YES,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            currentState.needFormStates.value =
                                EventScreenMainContract.NeedFormStates.NO
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.needFormStates.value =
                                    EventScreenMainContract.NeedFormStates.NO
                            },
                        state = currentState,
                        text = stringResource(R.string.no_have),
                        selected = currentState.needFormStates.value ==
                                EventScreenMainContract.NeedFormStates.NO,
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
                        state = currentState,
                        selected = currentState.priseSwitchButtonState.value,
                        onCheckedChange = { state.priseSwitchButtonState.value = it }
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
                        state = currentState,
                        selected = currentState.needBallSwitchButtonState.value,
                        onCheckedChange = { state.needBallSwitchButtonState.value = it }
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PhoneNumberInput(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.phoneNumberState.value,
                        onValueChange = { it ->
                            if (it.length <= Integers.NINE) {
                                state.phoneNumberState.value = it.filter { it.isDigit() }
                            }
                        },
                        enabled = state.isPhoneNumInputEnabled.value,
                        isError = when {
                            state.phoneNumberState.value.isInvalidValidPhoneNumber() -> true
                            else -> false
                        },
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(20.dp).clickable {
                                    state.isPhoneNumInputEnabled.value =
                                        !state.isPhoneNumInputEnabled.value
                                },
                                painter = if (!state.isPhoneNumInputEnabled.value) painterResource(R.drawable.ic_change_data) else painterResource(
                                    R.drawable.ic_done
                                ),
                                tint = primaryDark,
                                contentDescription = null,
                            )
                        },
                        errorMessage = when {
                            state.phoneNumberState.value.isInvalidValidPhoneNumber() -> stringResource(
                                id = R.string.phone_format_error
                            )

                            else -> {
                                ("")
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() })
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
                        .padding(top = 5.dp)
                        .height(104.dp)
                        .fillMaxWidth(),
                    state = currentState,
                    isSingleLine = false,
                    value = state.eventDescription.value,
                    onValueChange = { state.eventDescription.value = it },
                    transformation = VisualTransformation.None,
                    contentPaddingTop = 5.dp,
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
                        text = stringResource(R.string._3_3),
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
                    isEnabled = state.eventDescription.value.isNotEmpty(),
                    nextBtnOnClick = { publishBtnClicked() },
                    prevBtnOnClick = { backBtnCLicked() },
                    nextBtnOnTextId = R.string.publish,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterButton {
                    currentState.isBottomPreviewDrawerOpen.value = true
                }
                Spacer(modifier = Modifier.size(16.dp))
                InvitedUsersOfTheEventButton { currentState.isInvitedUsersDrawerOpen.value = true }
                when {
                    currentState.isBottomPreviewDrawerOpen.value -> bottomDrawerPreviewContent()
                    currentState.isInvitedUsersDrawerOpen.value -> invitedUsersModalContent()
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SuccessMessageCard(
                    text = currentState.successMessageText.value,
                    isMessageVisible = currentState.isSuccessMessageVisible.value,
                    onCancelIconClicked = {
                        currentState.isSuccessMessageVisible.value = false
                    }
                )
                ErrorMessageCard(
                    text = currentState.errorMessageText.value,
                    isMessageVisible = currentState.isErrorMessageVisible.value,
                    onCancelIconClicked = {
                        currentState.isErrorMessageVisible.value = false
                    }
                )
            }
        }
        if (currentState.state == EventScreenMainContract.ScreenViewState.Loading) {
            Loader()
        }
    }
}