package com.example.blanball.presentation.views.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.HomeScreenEventCardHorizontalList

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateToEvent: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 36.dp)
        ) {
            Row {
                Text(
                    text = "Привіт,", //TODO()
                    fontSize = 20.sp,
                    lineHeight = 32.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Юлія", //TODO()
                    fontSize = 20.sp,
                    lineHeight = 32.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
            }
            Spacer(Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.what_activities_are_we_planning_today),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(16.dp))
            HomeScreenEventCardHorizontalList(
                count = 10,
                clickToEventCardCallback = { navigateToEvent() },
            )
        }
    }
}