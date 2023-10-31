package com.example.blanball.presentation.views.components.bottomnavbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.blanball.presentation.navigation.BottomNavItem
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.surfaceBrandSecondary

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Events,
        BottomNavItem.CreateNewEvent,
        BottomNavItem.Rating,
        BottomNavItem.Chat,
    )
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                shadowElevation = 20f
            }
            .height(72.dp),
        backgroundColor = Color.White,
        elevation = 10.dp,
        contentColor = primaryDark,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = primaryDark,
                unselectedContentColor = primaryDark,
                icon = {
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (currentRoute == item.screen_route) surfaceBrandSecondary else Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .size(width = 64.dp, height = 32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = null,
                            tint = primaryDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}