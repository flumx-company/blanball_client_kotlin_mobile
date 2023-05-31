package com.example.blanball.presentation.views.widgets.colums

import DottedLine
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.theme.orangeStarColor
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.ext.formatDateReview

@Composable
fun DisplayUserReviewsColumn(state: PublicProfileMainContract.State) {
    Column {
        val reviews = state.reviewsList.value.take(3)
        for (review in reviews) {
            Column {
                DottedLine()
                Spacer(Modifier.size(16.dp))
                Row {
                    Text(
                        text = review.stars.toString(),
                        style = typography.h5,
                        fontSize = 16.sp,
                        color = orangeStarColor
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.full_star),
                        tint = orangeStarColor,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "${review.author.profile.name} ${review.author.profile.name}",
                        style = typography.h5,
                        fontSize = 13.sp,
                        color = secondaryNavy
                    )
                    Text(text = review.time_created.formatDateReview(), textAlign = TextAlign.End, style = typography.h5, color = secondaryNavy, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),)
                }
                Text(
                    text = review.text, style = typography.h5, color = primaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}

