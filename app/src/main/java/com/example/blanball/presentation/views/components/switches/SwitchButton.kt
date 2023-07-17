package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.mainGreen

@Composable
fun SwitchButton(
    state: StartScreensMainContract.State,
    selected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(checked = selected, onCheckedChange = onCheckedChange, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = mainGreen, checkedTrackAlpha = 1f), modifier = Modifier.size(32.dp, 16.dp))
}