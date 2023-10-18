package com.example.blanball.presentation.views.screens.futureevents

import DottedLine
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.NoHaveContentBanner
import com.example.blanball.presentation.views.components.buttons.Fab
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.handlers.InfiniteListHandler
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.switches.EventsSwitcher
import com.example.blanball.presentation.views.components.texts.TextBadge
import com.example.blanball.utils.ext.formatToUkrainianDate

@Composable
fun FutureEventsScreen(
    state: UiState,
    paddingValues: PaddingValues,
    navigateToEventScreen: () -> Unit,
    filterModalContent: @Composable () -> Unit,
    isFilterModalOpen: MutableState<Boolean>,
    onLoadMoreUsers: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val lazyListState = rememberLazyListState()

        val currentState: FutureEventsMainContract.State =
            (state as? FutureEventsMainContract.State) ?: FutureEventsMainContract.State(
                FutureEventsMainContract.ScreenViewState.Loading
            )
        (state as? FutureEventsMainContract.State)?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.future_events).uppercase(),
                    style = typography.h3,
                    color = primaryDark,
                    fontWeight = FontWeight(800),
                    lineHeight = 24.sp,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(12.dp))
                EventsSwitcher()
                Spacer(modifier = Modifier.size(12.dp))
                Row(verticalAlignment = CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = bgLight, shape = shapes.medium),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_sorting_new),
                            tint = secondaryNavy,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Спочатку нові",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = bgLight, shape = shapes.medium),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_search),
                            tint = secondaryNavy,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = bgLight, shape = shapes.medium),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                                .clickable { isFilterModalOpen.value = true },
                            painter = painterResource(id = R.drawable.ic_filters),
                            tint = secondaryNavy,
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                if (state.allEventsList.value.isEmpty()) {
                    NoHaveContentBanner(
                        headerTextId = R.string.not_found_events_for_this_filter,
                        secTextId = R.string.change_search_params
                    )
                } else {
                    LazyColumn {
                        itemsIndexed(state.allEventsList.value) { index, event ->
                            DefaultCardWithColumn(clickCallback = { navigateToEventScreen() }) {
                                Row {
                                    Box(
                                        Modifier
                                            .width(48.dp)
                                            .height(48.dp)
                                            .background(
                                                color = bgItemsGray,
                                                shape = shapes.small
                                            )
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .clip(shapes.small)
                                                .align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.ic_hands_emoji),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Column {
                                        Text(
                                            text = stringResource(id = R.string.friendly_match),
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            style = typography.h3,
                                            fontWeight = FontWeight(700),
                                            color = primaryDark,
                                        )
                                        Spacer(modifier = Modifier.size(6.dp))
                                        Row {
                                            Text(
                                                text = event.date_and_time.formatToUkrainianDate(),
                                                fontSize = 13.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = primaryDark,
                                            )
                                            Spacer(modifier = Modifier.size(12.dp))
                                            Text(
                                                text = event.date_and_time.toString(),
                                                fontSize = 13.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = secondaryNavy,
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.size(12.dp))
                                Row {
                                    Icon(
                                        modifier = Modifier.align(CenterVertically),
                                        painter = painterResource(id = R.drawable.ic_location),
                                        tint = mainGreen,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Text(
                                        text = event.place.place_name,
                                        fontSize = 12.sp,
                                        lineHeight = 20.sp,
                                        style = typography.h4,
                                        fontWeight = FontWeight(400),
                                        color = mainGreen,
                                    )
                                }
                                Spacer(modifier = Modifier.size(12.dp))
                                Text(
                                    text = event.description,
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(400),
                                    color = secondaryNavy,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.size(12.dp))
                                Row {
                                    TextBadge(textResId = R.string.football)
                                    Spacer(modifier = Modifier.size(4.dp))
                                    TextBadge(textResId = R.string.man_ukr)
                                    Spacer(modifier = Modifier.size(4.dp))
                                    TextBadge(textResId = R.string.withour_divison)
                                }
                                Spacer(modifier = Modifier.size(12.dp))
                                DottedLine(color = annotationGray)
                                Spacer(modifier = Modifier.size(12.dp))
                                Row {
                                    Text(
                                        text = event.name,
                                        fontSize = 13.sp,
                                        lineHeight = 24.sp,
                                        style = typography.h4,
                                        fontWeight = FontWeight(400),
                                        color = mainGreen,
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = stringResource(R.string.free),
                                        fontSize = 14.sp,
                                        lineHeight = 24.sp,
                                        style = typography.h4,
                                        fontWeight = FontWeight(600),
                                        color = primaryDark,
                                    )
                                }
                                Spacer(modifier = Modifier.size(16.dp))
                                Row {
                                    Column(modifier = Modifier.align(CenterVertically)) {
                                        Row {
                                            Text(
                                                text = stringResource(R.string.players),
                                                fontSize = 13.sp,
                                                lineHeight = 24.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(400),
                                                color = secondaryNavy,
                                            )
                                            Text(
                                                text = "${event.count_current_users}/${event.amount_members}",
                                                fontSize = 13.sp,
                                                lineHeight = 24.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(400),
                                                color = primaryDark,
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = stringResource(R.string.fans),
                                                fontSize = 13.sp,
                                                lineHeight = 24.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(400),
                                                color = secondaryNavy,
                                            )
                                            Text(
                                                text = "${event.count_current_fans}/${event.amount_members}",
                                                fontSize = 13.sp,
                                                lineHeight = 24.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(400),
                                                color = primaryDark,
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Button(
                                        onClick = { /*TODO*/ },
                                        shape = shapes.medium,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = mainGreen,
                                            contentColor = Color.White,
                                        ),
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.i_am_with_you),
                                            style = typography.h4,
                                            fontSize = 14.sp,
                                            lineHeight = 24.sp,
                                            fontWeight = FontWeight(400),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                }
                            }
                            if (state.isLoadingMoreAllEvents) {
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        CircularProgressIndicator(color = mainGreen)
                                    }
                                }
                            }
                            item {
                                InfiniteListHandler(
                                    lazyListState = lazyListState,
                                    onLoadMore = onLoadMoreUsers,
                                    buffer = 1
                                )
                            }
                    }
                }

            }
        }
        when {
            isFilterModalOpen.value -> filterModalContent()
        }
        Fab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        )
        if (currentState.state is FutureEventsMainContract.ScreenViewState.Loading) {
            Loader(backgroundColor = Color.White, textColor = primaryDark)
        }
    }
}