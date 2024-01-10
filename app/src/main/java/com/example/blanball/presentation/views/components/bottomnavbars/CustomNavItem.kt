package com.example.blanball.presentation.views.components.bottomnavbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blanball.presentation.navigation.BottomNavItem
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.surfaceBrandSecondary

@Composable
fun CustomNavItem(
    item: BottomNavItem,
    selectedItem: Boolean,
    navController: NavController,
) {
    Box(
        modifier = Modifier.fillMaxHeight().padding(horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (selectedItem) surfaceBrandSecondary else Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .size(width = 64.dp, height = 32.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate(item.screen_route)
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = item.icon),
                contentDescription = null,
                tint = primaryDark,
            )
        }
    }
}