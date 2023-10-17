package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Preview
@Composable
fun SelectEventDatesRangeButtons() {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = primaryDark, shape = RoundedCornerShape(size = 6.dp))
            .width(304.dp)
            .height(36.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 6.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_date),
                contentDescription = null,
                tint = secondaryNavy,
                )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "16 черв. 2022  |  25 черв. 2022",
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
            )
        }
    }
}