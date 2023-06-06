package com.example.blanball.presentation.views.components.ratingbars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.orangeStarColor

@Composable
fun RatingBar(rating: Float, maxRating: Int) {
    Row {
        repeat(maxRating) { index ->
            val vectorRes = if (index < rating) {
                R.drawable.full_star
            } else {
                R.drawable.empty_star
            }
            val vector = painterResource(id = vectorRes)
            Icon(painter = vector, contentDescription = null, tint = orangeStarColor)
            Spacer(modifier = Modifier.size(2.dp))
        }
    }
}