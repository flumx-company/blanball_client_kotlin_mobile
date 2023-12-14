package com.example.blanball.presentation.views.components.banners

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography


@Composable
fun NoMatchForYouQueryBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x1A3838FB),
                ambientColor = Color(0x1A3838FB)
            )
            .width(369.dp)
            .height(64.dp)
            .background(color = Color(0xFFFFFFFF), shape = shapes.small)
            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.no_match_for_you_query_banner),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(500),
                color = secondaryNavy,
            )
    }
}