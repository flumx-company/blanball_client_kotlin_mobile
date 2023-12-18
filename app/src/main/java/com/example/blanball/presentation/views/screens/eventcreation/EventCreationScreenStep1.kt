package com.example.blanball.presentation.views.screens.eventcreation

import DottedLine
import OutlineRadioButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.maps.SelectLocationWithGoogleMap
import com.example.blanball.presentation.views.components.switches.NewEventTimeSwitcher
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.utils.ext.getAddressFromLocation
import com.example.blanball.utils.ext.isNotValidErrorTopicField
import com.example.blanball.utils.ext.isValidErrorTopicField

@Composable
fun EventCreationScreenStep1(
    paddingValues: PaddingValues,
    state: UiState,
    navigateToSecondStep: () -> Unit,
    isBottomPreviewDrawerOpen: MutableState<Boolean>,
    isDatePickerModalOpen: MutableState<Boolean>,
    isStartTimePickerModalOpen: MutableState<Boolean>,
    isEndTimePickerModalOpen: MutableState<Boolean>,
    isInvitedUsersModalOpen: MutableState<Boolean>,
    bottomDrawerPreviewContent: @Composable () -> Unit,
    datePickerModalContent: @Composable () -> Unit,
    startTimePickerModalContent: @Composable () -> Unit,
    endTimePickerModalContent: @Composable () -> Unit,
    invitedUsersModalContent: @Composable () -> Unit,
    backBtnCLicked: () -> Unit,
) {
    val context = LocalContext.current
    val typesOfEvent = mutableListOf(
        stringResource(id = R.string.friendly_match)
    )
    val typesOfSports = mutableListOf(
        stringResource(id = R.string.football), stringResource(id = R.string.futsal)
    )
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
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.general_info),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                CustomDropDownMenu(
                    labelResId = R.string.event_type,
                    listItems = typesOfEvent,
                    value = it.eventType.value,
                    onValueChange = { state.eventType.value = it },
                    isError = when {
                        it.eventType.value.isEmpty() && it.isValidationActivated.value -> true
                        else -> false
                    },
                    errorMessage = when {
                        it.eventType.value.isEmpty() && it.isValidationActivated.value -> stringResource(
                            id = R.string.chose_event_type
                        )

                        else -> {
                            ("")
                        }
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.event_name,
                    state = it,
                    value = it.eventName.value,
                    onValueChange = { state.eventName.value = it },
                    transformation = VisualTransformation.None,
                    isError = when {
                        it.eventName.value.isNotValidErrorTopicField() -> true
                        else -> false
                    },
                    errorMessage = when {
                        it.eventName.value.isNotValidErrorTopicField() -> stringResource(R.string.validation_text_error_topic)

                        else -> {
                            ("")
                        }
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.gender_of_event_participants),
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
                            it.playersGenderStates.value =
                                EventCreationScreenMainContract.PlayersGenderStates.WOMANS
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.playersGenderStates.value =
                                    EventCreationScreenMainContract.PlayersGenderStates.WOMANS
                            },
                        state = it,
                        text = stringResource(id = R.string.woman_ukr),
                        selected = it.playersGenderStates.value == EventCreationScreenMainContract.PlayersGenderStates.WOMANS,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            it.playersGenderStates.value =
                                EventCreationScreenMainContract.PlayersGenderStates.MANS
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                it.playersGenderStates.value =
                                    EventCreationScreenMainContract.PlayersGenderStates.MANS
                            },
                        state = it,
                        text = stringResource(id = R.string.man_ukr),
                        selected = it.playersGenderStates.value == EventCreationScreenMainContract.PlayersGenderStates.MANS,
                        icon = null,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                CustomDropDownMenu(
                    labelResId = R.string.sport_type,
                    listItems = typesOfSports,
                    value = it.sportType.value,
                    onValueChange = { state.sportType.value = it },
                    isError = when {
                        it.sportType.value.isEmpty() && it.isValidationActivated.value -> true
                        else -> false
                    },
                    errorMessage = when {
                        it.sportType.value.isEmpty() && it.isValidationActivated.value -> stringResource(
                            id = R.string.chose_sport_type
                        )

                        else -> {
                            ("")
                        }
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.when_enent_happend),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(textFieldModifier = Modifier.fillMaxWidth(),
                    labelResId = R.string.date,
                    readOnly = true,
                    state = it,
                    onValueChange = {},
                    value = it.eventDateState.value,
                    interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    isDatePickerModalOpen.value = true
                                }
                            }
                        }
                    },
                    transformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    })
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.сhose_event_time),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.event_time_start,
                    modifier = Modifier.clickable { isStartTimePickerModalOpen.value = true },
                    state = it,
                    readOnly = true,
                    value = it.startEventTimeState.value ?: "",
                    onValueChange = {},
                    interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    isStartTimePickerModalOpen.value = true
                                }
                            }
                        }
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    },
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(20.dp))
                NewEventTimeSwitcher(selectedTime = state.eventDuration)
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.event_place),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.сhose_event_location),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                SelectLocationWithGoogleMap(
                    eventLocationLatLng = it.eventLocationLatLng,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = it.eventLocationLatLng.value.getAddressFromLocation(context) ?: "",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
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
                        text = stringResource(R.string._1_3),
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
                Spacer(modifier = Modifier.size(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.stepline_1),
                        contentDescription = null,
                        Modifier.weight(1f)
                    )
                    repeat(2) {
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(
                            painter = painterResource(id = R.drawable.empty_stepline),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                NextAndPreviousButtonsHorizontal(
                    isEnabled = it.eventName.value.isValidErrorTopicField()
                            && it.eventName.value.isNotEmpty()
                            && it.eventType.value.isNotEmpty()
                            && it.sportType.value.isNotEmpty()
                            && it.startEventTimeState.value.isNotEmpty()
                            && it.eventDuration.value != 0
                            && it.playersGenderStates.value != EventCreationScreenMainContract.PlayersGenderStates.NO_SELECT,
                    nextBtnOnClick = { navigateToSecondStep() },
                    prevBtnOnClick = { backBtnCLicked() },
                    nextBtnOnTextId = R.string.next,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterButton { isBottomPreviewDrawerOpen.value = true }
                Spacer(modifier = Modifier.size(16.dp))
                InvitedUsersOfTheEventButton { isInvitedUsersModalOpen.value = true }
                when {
                    isBottomPreviewDrawerOpen.value -> bottomDrawerPreviewContent()
                    isDatePickerModalOpen.value -> datePickerModalContent()
                    isInvitedUsersModalOpen.value -> invitedUsersModalContent()
                    isStartTimePickerModalOpen.value -> startTimePickerModalContent()
                }
            }
        }
    }
}