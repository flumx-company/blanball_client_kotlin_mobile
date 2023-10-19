package com.example.blanball.presentation.views.components.ratingbars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.orangeStarColor

@Composable
fun RatingBar(rating: Float, maxRating: Int, iconTint: Color = orangeStarColor) {
    Row {
        repeat(maxRating) { index ->
            val vectorRes = if (index < rating) {
                R.drawable.full_star
            } else {
                R.drawable.empty_star
            }
            val vector = painterResource(id = vectorRes)
            Icon(
                modifier = Modifier.size(24.dp),
                painter = vector,
                contentDescription = null,
                tint = iconTint
            )
            Spacer(modifier = Modifier.size(2.dp))
        }
    }
}


@Preview
@Composable
fun RatingBarUI() {
    RatingBar(rating = 4.5f, maxRating = 5)
}