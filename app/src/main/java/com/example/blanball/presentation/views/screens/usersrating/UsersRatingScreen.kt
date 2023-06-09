package com.example.blanball.presentation.views.screens.usersrating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.boxes.IcBox
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

    (state as? RatingUsersMainContract.State)?.let {
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
                Spacer(modifier = Modifier.size(20.dp))
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    IcBox(icon = R.drawable.ic_sorting)
                    Spacer(modifier = Modifier.size(4.dp))
                    Column(Modifier.wrapContentWidth()) {
                        Text(
                            text = stringResource(id = R.string.sorting),
                            style = typography.h5,
                            color = primaryDark,
                            fontSize = 14.sp,
                        )
                        Text(
                            text = stringResource(id = R.string.new_ones_first),
                            style = typography.h6,
                            color = secondaryNavy
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IcBox(icon = R.drawable.ic_filters)
                    Spacer(modifier = Modifier.size(4.dp))
                    Column(Modifier.wrapContentWidth()) {
                        Text(
                            text = stringResource(id = R.string.filters),
                            style = typography.h5,
                            color = primaryDark,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${stringResource(id = R.string.found)} 15 ${stringResource(id = R.string.ads)}",
                            style = typography.h6,
                            color = secondaryNavy
                        )
                    }
                }
                Spacer(modifier = Modifier.size(6.dp))
                LazyColumn {
                    items(state.usersList.value) {
                            user ->
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(text = "${user.profile.last_name} ${user.profile.name}", style = typography.h6, fontSize = 13.sp,  color = primaryDark)
                    }
                }
            }
        }
    }
}