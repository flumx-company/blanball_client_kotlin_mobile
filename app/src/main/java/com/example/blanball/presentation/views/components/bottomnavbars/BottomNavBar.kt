package com.example.blanball.presentation.views.components.bottomnavbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blanball.presentation.navigation.BottomNavItem
import com.example.blanball.presentation.theme.primaryDark

@Composable
fun BottomNavBar(
    navController: NavController,
    onCleanStatesCallback: () -> Unit,
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        backgroundColor = Color.White,
        elevation = 10.dp,
        contentColor = primaryDark,
    ) {
        val navigationItems = remember {
            listOf(
                BottomNavItem.Home,
                BottomNavItem.FutureEvents,
                BottomNavItem.CreateNewEvent,
                BottomNavItem.Rating,
                BottomNavItem.Chat,
            )
        }
        navigationItems.forEachIndexed { index, item ->
            CustomNavItem(
                item = item,
                selectedItem = navController.currentDestination?.route == item.screen_route,
                onItemClicked = {
                    when (index) {
                        2 -> {
                            onCleanStatesCallback()
                            navController.navigate(item.screen_route)
                        }

                        else -> navController.navigate(item.screen_route)
                    }
                },
            )
        }
    }
}