package com.example.blanball.presentation.views.components.ratingbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.ext.formatRatingToString

@Composable
fun RatingBarWithStarsAndNum(
    ratingValue: Float
) {
    Box (
        Modifier
            .fillMaxHeight()
            .wrapContentHeight()
            .background(color = Color.White, shape = RoundedCornerShape(size = 100.dp))
            .padding(start = 12.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    ){
        Row (verticalAlignment = Alignment.CenterVertically) {
            RatingBar(
                rating = ratingValue,
                maxRating = 5,
                iconTint = mainGreen,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = ratingValue.formatRatingToString(),
                style = typography.subtitle2,
                fontSize = 22.sp,
                color = primaryDark,
            )
            Text(
                text = stringResource(id = R.string.five_scores),
                style = typography.h5,
                fontSize = 14.sp,
                color = secondaryNavy,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}