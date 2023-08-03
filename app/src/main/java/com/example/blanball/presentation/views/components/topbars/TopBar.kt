package com.example.blanball.presentation.views.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark

@Composable
fun TopBar(
    navController: NavController,
    onNavIconClicked: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar (
        title = { Text(
            text = stringResource(id = R.string.blanball),
            color = primaryDark,
            )},
        backgroundColor = Color.White,
        contentColor = primaryDark,
        navigationIcon = {
            IconButton(onClick = onNavIconClicked) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}