package com.example.blanball.presentation.views.screens.usersrating

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.tabrows.ScrollableTabRow

@Composable
fun UsersRatingScreen(
    state: UiState,
) {
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_people),
        painterResource(id = R.drawable.ic_players),
        painterResource(id = R.drawable.ic_ball),
        painterResource(id = R.drawable.ic_timer),
        painterResource(id = R.drawable.ic_t_shirt)
    )
    val tabs: List<String> = listOf(
        stringResource(id = R.string.general),
        stringResource(id = R.string.players),
        stringResource(id = R.string.trainers),
        stringResource(id = R.string.referee),
        stringResource(id = R.string.teams)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)) {
            Text(
                text = stringResource(id = R.string.users_rating),
                style = typography.h2,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(20.dp))
            ScrollableTabRow(tabs = tabs, icons = icons)
        }
    }
}