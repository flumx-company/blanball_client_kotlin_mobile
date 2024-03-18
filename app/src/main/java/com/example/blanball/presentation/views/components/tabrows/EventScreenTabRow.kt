package com.example.blanball.presentation.views.components.tabrows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.itemsGrayBlue
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventTabRow(
    tabs: List<String>,
    icons: List<Painter>,
    contentItems: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by  remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.wrapContentWidth(),
        contentColor = mainGreen,
        backgroundColor = Color.White,
        edgePadding = 0.dp,
    ) {
        tabs.forEachIndexed { index, text ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = mainGreen,
                unselectedContentColor = itemsGrayBlue,
                onClick = {
                    selectedTabIndex = index
                },
            ) {
                Box(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                    Row(modifier = Modifier.wrapContentWidth()) {
                        Icon(
                            painter = icons[index],
                            contentDescription = null,
                            tint = if (selectedTabIndex == index) mainGreen else itemsGrayBlue,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = text,
                            style = typography.h4,
                            fontSize = 12.sp,
                            color = if (selectedTabIndex == index) mainGreen else itemsGrayBlue,
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
    HorizontalPager(
        state = pagerState, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { index ->
        Box(modifier = Modifier.wrapContentWidth().wrapContentSize()) {
            contentItems[index]()
        }
    }
}
