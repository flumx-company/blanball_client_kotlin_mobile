package com.example.blanball.presentation.views.components.ratingbars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.ext.formatRating

@Composable
fun RatingBarWithNum(
    ratingValue: Int
) {
    Row {
        Text(
            text = ratingValue.formatRating(),
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
        Spacer(modifier = Modifier.size(5.dp))
        Icon(
            painter = painterResource(id = R.drawable.full_star),
            tint = mainGreen,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(17.92.dp, 17.08.dp)
        )
    }
}