package com.example.blanball.presentation.views.components.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun TopBar(
    navController: NavController,
    onNavIconClicked: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar (
        title = {  Text(
            text = stringResource(id = R.string.blanball),
            style = typography.h3,
            lineHeight = 32.sp,
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            color = primaryDark
        )},
        backgroundColor = Color.White,
        contentColor = primaryDark,
        navigationIcon = {
            IconButton(onClick = onNavIconClicked) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = bgLight, shape = shapes.medium),
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_sidebar_menu),
                        tint = primaryDark,
                        contentDescription = null
                    )
                }
            }
        }
    )
}