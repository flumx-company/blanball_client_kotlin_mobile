package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

enum class EventTab {
    ALL_EVENTS,
    MY_EVENTS
}

@Composable
fun EventsSwitcher() {
    var selectedTab by remember { mutableStateOf(EventTab.ALL_EVENTS) }

    Box(
        Modifier
            .background(color = bgItemsGray, shape = RoundedCornerShape(size = 6.dp))
            .padding(2.dp)
            .fillMaxWidth()
    )
    {
        Row {
            Button(
                onClick = {
                    selectedTab = EventTab.ALL_EVENTS
                },
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp),
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedTab == EventTab.ALL_EVENTS) Color.White else bgItemsGray,
                    contentColor = if (selectedTab == EventTab.ALL_EVENTS) primaryDark else secondaryNavy,
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.all),
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                )
            }
            Button(
                onClick = {
                    selectedTab = EventTab.MY_EVENTS
                },
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp),
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedTab == EventTab.MY_EVENTS) Color.White else bgItemsGray,
                    contentColor = if (selectedTab == EventTab.MY_EVENTS) primaryDark else secondaryNavy,
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.my_events),
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                )
            }
        }
    }
}