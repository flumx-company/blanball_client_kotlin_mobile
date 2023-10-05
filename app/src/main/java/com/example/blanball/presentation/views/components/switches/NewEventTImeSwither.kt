package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun NewEventTimeSwitcher(
    selectedTime: MutableState<Int>
) {

    val timeOptions = listOf(
        30,
        60,
        90,
        120,
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = defaultLightGray, shape = shapes.medium)
            .padding(4.dp)
            .height(32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            timeOptions.forEach { timeInMinutes ->
                val isSelected = timeInMinutes == selectedTime.value

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                        .background(
                            color = if (isSelected) Color.Blue else Color.White,
                            shape = shapes.small
                        )
                        .height(32.dp)
                        .clickable {
                            selectedTime.value = timeInMinutes
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text ="$timeInMinutes хв" ,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = if (isSelected) Color.White else secondaryNavy,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}