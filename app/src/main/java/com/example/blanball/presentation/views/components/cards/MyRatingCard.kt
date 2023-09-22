package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.surfaceDarkerBrand
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.ratingbars.RatingBarWithStarsAndNum

@Composable
fun MyRatingCard() {
    Box (
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = surfaceDarkerBrand, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.my_rating),
                fontSize = 18.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
            )
            Spacer(Modifier.size(8.dp))
            RatingBarWithStarsAndNum(ratingValue = 4.7f)
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Переглянути відгуки (17)",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy,
                    textAlign = TextAlign.Center,
                )
        }
    }
}