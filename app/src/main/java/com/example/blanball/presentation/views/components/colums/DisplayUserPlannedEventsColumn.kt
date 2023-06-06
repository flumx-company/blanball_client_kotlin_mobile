package com.example.blanball.presentation.views.components.colums

import DottedLine
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.theme.bgLight2
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.ext.formatDatePlannedEvents
import com.example.blanball.utils.ext.formatDatePlannedEventsToTime

@Composable
fun DisplayUserPlannedEventsColumn(state: PublicProfileMainContract.State) {
    Column {
        val plannedEvents = state.plannedEventsList.value.take(3)
        for (plannedEvent in plannedEvents) {
            Column {
                DottedLine()
                Spacer(modifier = Modifier.size(16.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.tournament),
                        style = typography.h3,
                        color = primaryDark
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = plannedEvent.pk_user_role,
                        style = typography.h6,
                        color = primaryDark,
                        modifier = Modifier
                            .background(
                                bgLight2,
                                shapes.small
                            )
                            .padding(start = 4.dp, end = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.size(6.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween)  {
                    Text(
                        text = plannedEvent.type,
                        style = typography.h6,
                        color = primaryDark,
                        modifier = Modifier
                            .border(
                                1.dp,
                                color = defaultLightGray,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .padding(start = 8.dp, end = 8.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = plannedEvent.gender, style = typography.h6,
                        color = primaryDark,
                        modifier = Modifier
                            .border(
                                1.dp,
                                color = defaultLightGray,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .padding(start = 8.dp, end = 8.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(id = R.string.three_dots), style = typography.h6,
                        color = primaryDark,
                        modifier = Modifier
                            .border(
                                1.dp,
                                color = defaultLightGray,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .padding(start = 8.dp, end = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_1),
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                }
                Spacer(modifier = Modifier.size(6.dp))
                Row {
                    Text(
                        text = plannedEvent.date_and_time.formatDatePlannedEvents(),
                        style = typography.h5,
                        color = secondaryNavy
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = "${plannedEvent.date_and_time.formatDatePlannedEventsToTime()} - ${
                            plannedEvent.date_and_time.formatDatePlannedEventsToTime(
                                plannedEvent.duration
                            )
                        }", style = typography.h5,
                        color = secondaryNavy
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
        }
    }
}
}