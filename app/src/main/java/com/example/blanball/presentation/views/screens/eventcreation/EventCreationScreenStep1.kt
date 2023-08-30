package com.example.blanball.presentation.views.screens.eventcreation

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtons
import com.example.blanball.presentation.views.components.cards.PreviewOfTheEventPosterCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput

@Composable
fun EventCreationScreenStep1(
    paddingValues: PaddingValues,
    state: UiState,
) {
    val typesOfEvent = mutableListOf(
        stringResource(id = R.string.friendly_match)
    )
    val typesOfSports = mutableListOf(
        stringResource(id = R.string.football),
        stringResource(id = R.string.futsal)
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
                Spacer(modifier = Modifier.size(20.dp))
                DefaultTextInput(
                    labelResId = R.string.event_name,
                    state = it,
                    value = it.eventName.value,
                    onValueChange = { state.eventName.value = it },
                    transformation = VisualTransformation.None
                )
                Spacer(modifier = Modifier.size(16.dp))
                CustomDropDownMenu(
                    labelResId = R.string.event_type,
                    listItems = typesOfEvent,
                    value = it.typeOfEvent.value,
                    onValueChange = { state.typeOfEvent.value = it },
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.gender_of_event_participants),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
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
                            .weight(0.8f)
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
                    OutlineRadioButton(
                        onClick = {
                            it.playersGenderStates.value =
                                EventCreationScreenMainContract.PlayersGenderStates.ALL
                        },
                        modifier = Modifier
                            .weight(0.7f)
                            .clickable {
                                it.playersGenderStates.value =
                                    EventCreationScreenMainContract.PlayersGenderStates.ALL
                            },
                        state = it,
                        text = stringResource(id = R.string.all),
                        selected = it.playersGenderStates.value == EventCreationScreenMainContract.PlayersGenderStates.ALL,
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
                DefaultTextInput(
                    labelResId = R.string.date_and_time_of_event,
                    state = it,
                    value = it.timeAndDateOfEvent.value,
                    onValueChange = { state.timeAndDateOfEvent.value = it },
                    transformation = VisualTransformation.None
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.place_of_event,
                    state = it,
                    value = it.placeOfEvent.value,
                    onValueChange = { state.placeOfEvent.value = it },
                    transformation = VisualTransformation.None
                )
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
                Spacer(modifier = Modifier.size(24.dp))
                NextAndPreviousButtons(
                    isEnabled = true,
                    nextBtnOnClick = { /*TODO*/ },
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