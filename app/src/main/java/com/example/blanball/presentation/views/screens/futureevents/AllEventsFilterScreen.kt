package com.example.blanball.presentation.views.screens.futureevents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.SelectEventDatesRangeButtons
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu

@Composable
fun AllEventsFilterScreen(
    state: UiState,
    isDatePickerModalOpen: MutableState<Boolean>,
    datePickerModalContent: @Composable () -> Unit,
    turnBackBtnClicked: () -> Unit,
    confirmBtnClicked: () -> Unit,
    paddingValues: PaddingValues,
) {
    val typeOfSports = listOf(
        stringResource(id = R.string.football),
        stringResource(id = R.string.futsal),
    )
    (state as FutureEventsMainContract.State).let {
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.filters),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Text(
                    text = "${state.allEventsCounter.value} " + stringResource(id = R.string.ads),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Divider(color = defaultLightGray)
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    labelResId = R.string.sport_type,
                    listItems = typeOfSports,
                    value = it.typeOfSportsStateSelected.value,
                    onValueChange = { state.typeOfSportsStateSelected.value = it }
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                state.gendersSelectionState.value =
                                    FutureEventsMainContract.GenderSelectionState.MALE
                            }
                            .border(
                                width = 1.dp,
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.MALE) mainGreen else defaultLightGray,
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .wrapContentWidth()
                            .height(32.dp)
                            .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.male_ic),
                                contentDescription = null,
                                tint = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.MALE) mainGreen else secondaryNavy,
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = stringResource(id = R.string.man_ukr),
                                fontSize = 13.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.MALE) mainGreen else primaryDark,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .clickable {
                                state.gendersSelectionState.value =
                                    FutureEventsMainContract.GenderSelectionState.FEMALE
                            }
                            .border(
                                width = 1.dp,
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.FEMALE) mainGreen else defaultLightGray,
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .wrapContentWidth()
                            .height(32.dp)
                            .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.female_ic),
                                contentDescription = null,
                                tint = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.FEMALE) mainGreen else secondaryNavy

                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = stringResource(id = R.string.woman_ukr),
                                fontSize = 13.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.FEMALE) mainGreen else primaryDark,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .clickable {
                                state.gendersSelectionState.value =
                                    FutureEventsMainContract.GenderSelectionState.ALL
                            }
                            .border(
                                width = 1.dp,
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.ALL) mainGreen else defaultLightGray,
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .wrapContentWidth()
                            .height(32.dp)
                            .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_all),
                                contentDescription = null,
                                tint = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.ALL) mainGreen else secondaryNavy,
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = stringResource(id = R.string.all),
                                fontSize = 13.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = if (state.gendersSelectionState.value == FutureEventsMainContract.GenderSelectionState.ALL) mainGreen else primaryDark,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                SelectEventDatesRangeButtons(
                    clickCallback = { isDatePickerModalOpen.value = true },
                    state = state,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { turnBackBtnClicked() }) {
                        Text(
                            text = stringResource(id = R.string.clean),
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(500),
                            color = secondaryNavy,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { confirmBtnClicked() },
                        shape = shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = mainGreen,
                            contentColor = Color.White,
                        )
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null
                            )
                            Text(
                                text = stringResource(id = R.string.apply),
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
        when {
            isDatePickerModalOpen.value -> datePickerModalContent()
        }
    }
}