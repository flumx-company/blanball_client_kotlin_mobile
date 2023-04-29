package com.example.blanball.presentation.views.widgets.switches

import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.theme.selectedDarkGray

@Composable
fun SwitchButton(
    state: MainContract.State,
    selected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(checked = selected, onCheckedChange = onCheckedChange, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = selectedDarkGray), modifier = Modifier.size(32.dp, 16.dp))
}