package com.example.blanball.presentation.views.screens.eventcreation

import DottedLine
import OutlineRadioButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.cards.PreviewOfTheEventPosterCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.switches.NewEventTimeSwitcher
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput

@Composable
fun EventCreationScreenStep1(
    paddingValues: PaddingValues,
    state: UiState,
    navigateToSecondStep: () -> Unit,
) {
    val typesOfEvent = mutableListOf(
        stringResource(id = R.string.friendly_match)
    )
    val typesOfSports = mutableListOf(
        stringResource(id = R.string.football),
        stringResource(id = R.string.futsal)
    )

    val datePickerDialogOpened = remember { mutableStateOf(false) }


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
                    value = it.typeOfEvent.value,
                    onValueChange = { state.typeOfEvent.value = it },
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.event_name,
                    state = it,
                    value = it.eventName.value,
                    onValueChange = { state.eventName.value = it },
                    transformation = VisualTransformation.None
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
                        text = stringResource(id = R.string.womans),
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
                        text = stringResource(id = R.string.mans),
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
                DefaultTextInput(
                    modifier = Modifier.clickable {  },
                    labelResId = R.string.date,
                    state = it,
                    value = it.eventDateState.value ,
                    onValueChange = {} ,
                    transformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    }
                )
                NewEventTimeSwitcher()
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    modifier = Modifier
                        .border(width = 1.dp, color = defaultLightGray, shape = shapes.medium)
                        .fillMaxWidth()
                        .height(160.dp)
                        .clickable { },
                    painter = painterResource(id = R.drawable.temp_map_image), //TODO()
                    contentDescription = null
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
                        text = "1 / 4", //TODO()
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
                Spacer(modifier = Modifier.size(4.dp))
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
                    isEnabled = true,
                    nextBtnOnClick = { navigateToSecondStep() },
                    prevBtnOnClick = { /*TODO*/ },
                    nextBtnOnTextId = R.string.next,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterCard {}
            }
        }
    }
}