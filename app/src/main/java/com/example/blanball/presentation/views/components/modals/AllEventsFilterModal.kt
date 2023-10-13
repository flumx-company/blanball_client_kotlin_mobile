package com.example.blanball.presentation.views.components.modals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu

@Composable
fun AllEventsFilterModal(
    state: UiState,
    turnBackBtnClicked: () -> Unit,
    confirmBtnClicked: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    val typeOfEvents = listOf(
        stringResource(id = R.string.friendly_match)
    )
    val typeOfSports = listOf(
        stringResource(id = R.string.football),
        stringResource(id = R.string.futsal)
    )
    (state as? FutureEventsMainContract.State)?.let {
        AlertDialog(modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 20.dp)
            .wrapContentHeight(),
            onDismissRequest = { /*TODO*/ }, buttons = {}, text = {
                Column {
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
                        text = "15 оголошень",
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                    )
                    Divider(color = annotationGray)
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.event_type,
                        listItems = typeOfEvents,
                        value = it.typeOfEventsStateSelected.value,
                        onValueChange = { state.typeOfEventsStateSelected.value = it }
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.sport_type,
                        listItems = typeOfSports,
                        value = it.typeOfSportsStateSelected.value,
                        onValueChange = { state.typeOfSportsStateSelected.value = it }
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Row {

                    }
                    Spacer(modifier = Modifier.size(12.dp))
//                    CustomDropDownMenu(
//                        labelResId = R.string.,
//                        listItems =,
//                        value =,
//                        onValueChange = .
//                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
        )
    }
}