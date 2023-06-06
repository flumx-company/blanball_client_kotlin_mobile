package com.example.blanball.presentation.views.screens.publicprofile

import DottedLine
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
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
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.orangeStarColor
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.handlers.InfiniteListHandler
import com.example.blanball.utils.ext.formatDateReview

@Composable
fun AllReviewsScreen(
    state: UiState,
    onLoadMoreReviews: () -> Unit,
) {
    (state as? PublicProfileMainContract.State)?.let {
        val lazyListState = rememberLazyListState()
        LazyColumn(
            Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 20.dp
            )
        ) {
            items(state.reviewsList.value) { review ->
                Column {
                    DottedLine()
                    Spacer(Modifier.size(12.dp))
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
                        Text(
                            text = review.time_created.formatDateReview(),
                            textAlign = TextAlign.End,
                            style = typography.h5,
                            color = secondaryNavy,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        )
                    }
                    Text(
                        text = review.text,
                        style = typography.h5,
                        color = primaryDark,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    )
                    Spacer(Modifier.size(12.dp))
                }
            }
            if (state.isLoadingMoreReviews) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = mainGreen)
                    }
                }
            }
            item {
                InfiniteListHandler(
                    lazyListState = lazyListState,
                    onLoadMore = onLoadMoreReviews
                )
            }
        }
    }
}
