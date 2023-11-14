package com.example.blanball.presentation.views.components.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.typography

@Composable
fun MyProfileMainGreenTextBadge(
    text: String,
) {
    Box(
        modifier = Modifier
            .background(color = mainGreen, shape = RoundedCornerShape(size = 100.dp))
            .padding(start = 4.dp, top = 2.dp, end = 4.dp, bottom = 2.dp)
            .wrapContentSize()
    )
    {
        Row {
            Text(
                text = stringResource(R.string.hashtag),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = Color.White,
            )
        }
    }
}