package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.MyEventsScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.ext.toFormattedDate

@Composable
fun SelectEventDatesRangeButtons(
    clickCallback: () -> Unit,
    state: UiState,
) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = primaryDark, shape = RoundedCornerShape(size = 6.dp))
            .fillMaxWidth()
            .height(36.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 6.dp)
            )
            .clickable { clickCallback() }
            .padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_date),
                contentDescription = null,
                tint = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = when (state) {
                    is FutureEventsMainContract.State -> {
                        state.filterDateAndTimeAfter.value.toFormattedDate() + " | " + state.filterDateAndTimeBefore.value.toFormattedDate()
                    }

                    is MyEventsScreenMainContract.State -> {
                        state.filterDateAndTimeAfter.value.toFormattedDate() + " | " + state.filterDateAndTimeBefore.value.toFormattedDate()
                    }

                    else -> {
                        ""
                    }
                },
                fontSize = 14.sp,
                lineHeight = 24.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = primaryDark,
                textAlign = TextAlign.Center,
            )
        }
    }
}