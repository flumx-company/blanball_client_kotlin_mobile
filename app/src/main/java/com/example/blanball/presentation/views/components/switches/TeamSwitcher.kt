package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.classicBlue
import com.example.blanball.presentation.theme.classicRed
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

private enum class TeamTab {
    FIRST_TEAM,
    SECOND_TEAM,
}

@Composable
fun TeamSwitcher(
    firstTeamName: String,
    secondTeamName: String,
) {
    var selectedTab by remember { mutableStateOf(TeamTab.FIRST_TEAM) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 6.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 6.dp
                    )
                )
                .border(
                    width = 1.dp,
                    color = if (selectedTab == TeamTab.FIRST_TEAM) classicRed else secondaryNavy,
                    shape = RoundedCornerShape(
                        topStart = 6.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 6.dp
                    )
                )
                .width(108.dp)
                .height(32.dp)
                .clickable { selectedTab = TeamTab.FIRST_TEAM },
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = firstTeamName,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                color = if (selectedTab == TeamTab.FIRST_TEAM) classicRed else secondaryNavy,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            )
        }
        Box(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 6.dp,
                    bottomEnd = 6.dp,
                    bottomStart = 0.dp
                )
            )
            .border(
                width = 1.dp,
                color = if (selectedTab == TeamTab.SECOND_TEAM) classicBlue else secondaryNavy,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 6.dp,
                    bottomEnd = 6.dp,
                    bottomStart = 0.dp
                )
            )
            .width(108.dp)
            .height(32.dp)
            .clickable { selectedTab = TeamTab.SECOND_TEAM },
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = secondTeamName,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                color = if (selectedTab == TeamTab.SECOND_TEAM) classicBlue else secondaryNavy,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            )
        }
    }
}