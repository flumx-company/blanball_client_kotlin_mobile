package com.example.blanball.presentation.views.components.texts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography

@Composable
fun TextBadge2(
    text: String,
) {
    if (text.isNotEmpty()) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = defaultLightGray,
                    shape = RoundedCornerShape(size = 100.dp)
                )
                .wrapContentSize()
        )
        {
            Row {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = text,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}