package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

private enum class EventTab {
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
            Box(
                modifier = Modifier
                    .clickable {
                        selectedTab = EventTab.ALL_EVENTS
                    }
                    .weight(1f)
                    .height(32.dp)
                    .background(
                        color = if (selectedTab == EventTab.ALL_EVENTS) Color.White else bgItemsGray,
                        shape = shapes.medium
                    ),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = stringResource(id = R.string.all),
                    style = typography.h4,
                    color = if (selectedTab == EventTab.ALL_EVENTS) primaryDark else secondaryNavy,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                )
            }
            Box(
                modifier = Modifier
                    .clickable {
                        selectedTab = EventTab.MY_EVENTS
                    }
                    .weight(1f)
                    .height(32.dp)
                    .background(
                        color = if (selectedTab == EventTab.MY_EVENTS) Color.White else bgItemsGray,
                        shape = shapes.medium
                    ),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = stringResource(id = R.string.my_events),
                    style = typography.h4,
                    color = if (selectedTab == EventTab.ALL_EVENTS) primaryDark else secondaryNavy,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                )
            }
        }
    }
}