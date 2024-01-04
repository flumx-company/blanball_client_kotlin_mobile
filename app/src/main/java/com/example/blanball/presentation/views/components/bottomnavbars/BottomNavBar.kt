package com.example.blanball.presentation.views.components.bottomnavbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.blanball.presentation.navigation.BottomNavItem
import com.example.blanball.presentation.theme.primaryDark


@Composable
fun BottomNavBar(navController: NavController, listOfItems: List<BottomNavItem>) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            backgroundColor = Color.White,
            elevation = 10.dp,
            contentColor = primaryDark,
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            listOfItems.forEach { item ->
                    CustomNavItem(
                        currentRoute = currentRoute,
                        item = item,
                        navController = navController,
                    )
            }
        }
    }