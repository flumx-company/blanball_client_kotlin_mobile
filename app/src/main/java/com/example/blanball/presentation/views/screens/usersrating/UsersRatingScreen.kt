package com.example.blanball.presentation.views.screens.usersrating

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.bgLight2
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.boxes.IcBox
import com.example.blanball.presentation.views.components.dropdownmenu.OutlineDropDownMenu
import com.example.blanball.presentation.views.components.handlers.InfiniteListHandler
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.loaders.MainGreenCircularProgressIndicator
import com.example.blanball.presentation.views.components.ratingbars.RatingBar
import com.example.blanball.presentation.views.components.sliders.SteppedSlider
import com.example.blanball.presentation.views.components.tabrows.ScrollableTabRow
import com.example.blanball.utils.ext.formatRatingToFloat
import com.example.blanball.utils.ext.toIntRange

@Composable
fun UsersRatingScreen(
    state: UiState,
    onLoadMoreUsers: () -> Unit,
    onClickedToLoadWithNewFilters: () -> Unit,
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

    val expandedStateMap = remember { mutableStateMapOf<Int, Boolean>() }
    val configuration = LocalConfiguration.current
    val currentState: RatingUsersMainContract.State =
        (state as? RatingUsersMainContract.State) ?: RatingUsersMainContract.State(
            RatingUsersMainContract.ScreenViewState.Loading)

    (state as? RatingUsersMainContract.State)?.let {
        val lazyListState = rememberLazyListState()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 0.dp)) {
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
                    IcBox(
                        icon = R.drawable.ic_filters,
                        modifier = Modifier
                            .clickable(onClick ={ it.openFiltersDialog.value = true })
                            .size(40.dp)
                            .background(color = bgLight, shape = shapes.medium)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Column(
                        Modifier
                            .wrapContentWidth()
                            .weight(1f)) {
                        Text(
                            text = stringResource(id = R.string.filters),
                            style = typography.h5,
                            color = primaryDark,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${stringResource(id = R.string.found)} 15",
                            style = typography.h6,
                            color = secondaryNavy
                        )
                    }
                }
                Spacer(modifier = Modifier.size(6.dp))
                LazyColumn {
                    itemsIndexed(state.usersList.value) { index, user ->
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.clickable {
                                expandedStateMap[index] = !(expandedStateMap[index] ?: false)
                            },
                        ) {
                            if (user.profile.avatar_url.isNullOrEmpty()) {
                                Box(
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.circle_avatar),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .fillMaxSize()
                                    )
                                    Text(
                                        text = "${user.profile.last_name.firstOrNull() ?: ""}${user.profile.name.firstOrNull() ?: ""}",
                                        modifier = Modifier.align(
                                            Alignment.Center
                                        ),
                                        style = typography.h2, fontSize = 16.sp, color = mainGreen
                                    )
                                }
                            } else {
                                Image(
                                    painter = rememberAsyncImagePainter(user.profile.avatar_url),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .size(36.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.size(12.dp))
                            Column() {
                                Text(
                                    text = "${user.profile.last_name} ${user.profile.name}",
                                    style = typography.h6,
                                    fontSize = 13.sp,
                                    color = primaryDark,
                                    modifier = Modifier.width(140.dp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Clip,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                RatingBar(
                                    rating = user.raiting?.formatRatingToFloat() ?: 0f,
                                    maxRating = 5
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column(Modifier.wrapContentWidth()) {
                                Text(
                                    text = stringResource(id = R.string.blanball_team),
                                    style = typography.h6,
                                    color = primaryDark
                                )
                                Spacer(modifier = Modifier.size(2.dp))
                                Text(
                                    text = "${user.role}",
                                    style = typography.h6,
                                    fontSize = 14.sp,
                                    color = secondaryNavy
                                )
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Icon(
                                painter = if (expandedStateMap[index] == true) painterResource(id = R.drawable.ic_arrow_up) else painterResource(
                                    id = R.drawable.ic_arrow_down
                                ), modifier = Modifier.size(11.25.dp, 6.25.dp),
                                tint = primaryDark,
                                contentDescription = null
                            )
                        }
                        if (expandedStateMap[index] == true) {
                            Row {
                                Text(
                                    text = "${user.profile.position ?: ""}",
                                    style = typography.h5,
                                    fontSize = 12.sp,
                                    color = primaryDark
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                when {
                                    user.profile.gender != null && user.profile.gender == stringResource(
                                        id = R.string.man
                                    ) -> IcBox(
                                        icon = R.drawable.male_ic, modifier = Modifier
                                            .size(20.dp)
                                            .background(color = bgLight, shape = shapes.medium)
                                    )

                                    user.profile.gender != null && user.profile.gender == stringResource(
                                        id = R.string.woman
                                    ) -> IcBox(
                                        icon = R.drawable.female_ic, modifier = Modifier
                                            .size(20.dp)
                                            .background(color = bgLight, shape = shapes.medium)
                                    )

                                    else -> {}
                                }
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    user.profile.gender ?: "",
                                    style = typography.h5,
                                    fontSize = 12.sp,
                                    color = secondaryNavy
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Divider(color = bgLight2, thickness = 1.dp)
                    }
                    if (state.isLoadingMoreUsers) {
                        item {
                            MainGreenCircularProgressIndicator()
                        }
                    }
                    item {
                        InfiniteListHandler(
                            lazyListState = lazyListState,
                            onLoadMore = onLoadMoreUsers,
                            buffer = 2
                        )
                    }
                }
            }
            if (it.openFiltersDialog.value) {
                AlertDialog(
                    modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = Color.White,
                    title = {
                        Column() {
                            Text(
                                text = stringResource(id = R.string.filters),
                                style = typography.h5,
                                color = primaryDark,
                            )
                            Text(
                                text = "${stringResource(id = R.string.found)} 15 ${
                                    stringResource(
                                        id = R.string.users
                                    )
                                }",
                                style = typography.h6,
                                color = secondaryNavy
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = onClickedToLoadWithNewFilters ,
                            shape = shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = mainGreen,
                                contentColor = Color.White,
                            ),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_apply),
                                    tint = Color.White,
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(
                                    text = stringResource(id = R.string.apply),
                                    style = typography.h5
                                )
                            }
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { it.openFiltersDialog.value = false }) {
                            Text(
                                text = stringResource(id = R.string.clean),
                                style = typography.h4,
                                fontSize = 14.sp,
                                color = secondaryNavy
                            )
                        }
                    },
                    onDismissRequest = {
                    },
                    text = {
                        Column() {
                            OutlineDropDownMenu()
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                text = stringResource(id = R.string.gender),
                                style = typography.h6,
                                fontSize = 13.sp,
                                color = secondaryNavy
                            )
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                OutlinedButton(
                                    onClick = {
                                               it.genderSelectionState.value = RatingUsersMainContract.GenderSelectionState.MALE
                                    },
                                    modifier = Modifier.wrapContentWidth(),
                                    border = if (it.genderSelectionState.value == RatingUsersMainContract.GenderSelectionState.MALE) BorderStroke(
                                        2.dp,
                                        mainGreen
                                    ) else BorderStroke(2.dp, defaultLightGray)
                                ) {
                                    Row {
                                        Text(
                                            text = stringResource(id = R.string.male),
                                            style = typography.h6,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(8.dp))
                                OutlinedButton(
                                    onClick = {
                                        it.genderSelectionState.value = RatingUsersMainContract.GenderSelectionState.FEMALE
                                    },
                                    modifier = Modifier.wrapContentWidth(),
                                    border = if (it.genderSelectionState.value == RatingUsersMainContract.GenderSelectionState.FEMALE) BorderStroke(
                                        2.dp,
                                        mainGreen
                                    ) else BorderStroke(2.dp, defaultLightGray)
                                ) {
                                    Row {
                                        Text(
                                            text = stringResource(id = R.string.female),
                                            style = typography.h6,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(8.dp))
                                OutlinedButton(
                                    onClick = {
                                        it.genderSelectionState.value = RatingUsersMainContract.GenderSelectionState.ALL
                                    },
                                    modifier = Modifier.wrapContentWidth(),
                                    border = if (it.genderSelectionState.value == RatingUsersMainContract.GenderSelectionState.ALL) BorderStroke(
                                        2.dp,
                                        mainGreen
                                    ) else BorderStroke(2.dp, defaultLightGray)
                                ) {
                                    Row() {
                                        Text(
                                            text = stringResource(id = R.string.all),
                                            style = typography.h6,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                            Text(
                                text = stringResource(id = R.string.age_range),
                                style = typography.h6,
                                fontSize = 13.sp,
                                color = secondaryNavy
                            )
                            Text(
                                text = "${state.ageSliderPosition.value.toIntRange()}",
                                style = typography.h6,
                                fontSize = 13.sp,
                                color = secondaryNavy
                            )
                            SteppedSlider(
                                value = it.ageSliderPosition.value,
                                onValueChange = { state.ageSliderPosition.value = it })
                        }
                    })
            }
        }
    }
    if (currentState.state is RatingUsersMainContract.ScreenViewState.Loading || currentState.state is RatingUsersMainContract.ScreenViewState.LoadingWithFilters) {
        Loader(backgroundColor = Color.White, textColor = primaryDark)
    }
}