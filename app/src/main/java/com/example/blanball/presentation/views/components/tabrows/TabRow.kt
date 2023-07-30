package com.example.blanball.presentation.views.components.tabrows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.itemsGrayBlue
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.typography

@Composable
fun TabRow(tabs: List<String>, icons: List<Painter>, modifier: Modifier? = null) {
    val selectedTab = remember { mutableStateOf(0) }
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value,
        modifier = Modifier.wrapContentWidth(),
        contentColor = mainGreen,
        backgroundColor = Color.White,
        edgePadding = 0.dp,
    ) {
        tabs.forEachIndexed { index, text ->
            Tab(
                selected = selectedTab.value == index,
                selectedContentColor = mainGreen,
                unselectedContentColor = itemsGrayBlue,
                onClick = {
                    selectedTab.value = index
                },
            ) {
                Box(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                    Row() {
                        Icon(
                            painter = icons[index],
                            contentDescription = null,
                            tint = if (selectedTab.value == index) mainGreen else itemsGrayBlue,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(15.dp, 12.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = text,
                            style = typography.h5,
                            fontSize = 12.sp,
                            color = if (selectedTab.value == index) mainGreen else itemsGrayBlue,
                        )
                    }
                }
            }
        }
    }
}