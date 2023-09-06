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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.cards.PreviewOfTheEventPosterCard
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.PhoneNumberInput
import com.example.domain.utils.Integers

@Composable
fun EventCreationScreenStep3(
    paddingValues: PaddingValues,
    state: UiState,
) {
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
                    Text(
                        text = stringResource(R.string.will_there_be_a_prize_draw),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                    )
                Spacer(modifier = Modifier.size(16.dp))
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
PhoneNumberInput(
    value = it.phoneNumberState.value,
    onValueChange = { it ->
                if (it.length <= Integers.NINE) {
                    state.phoneNumberState.value = it.filter { it.isDigit() }
                }
            },
    modifier = Modifier
        .fillMaxWidth(),
    keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() })
)
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
                    nextBtnOnClick = { /*TODO*/ },
                    prevBtnOnClick = { /*TODO*/ },
                    nextBtnOnTextId = R.string.publish,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterCard {}
            }
        }
    }
}