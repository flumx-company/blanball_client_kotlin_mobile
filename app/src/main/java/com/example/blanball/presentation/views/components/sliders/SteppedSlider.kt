package com.example.blanball.presentation.views.components.sliders

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SteppedSlider(
    modifier: Modifier = Modifier,
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    RangeSlider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 6f..80f,
        steps = 74,
        colors = SliderDefaults.colors(thumbColor = mainGreen, activeTrackColor = defaultLightGray, activeTickColor = mainGreen  )
    )
}