package com.example.blanball.presentation.views.components.switches

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun NewEventTimeSwitcher() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = defaultLightGray, shape = shapes.medium)
            .padding(4.dp)
            .height(32.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
           Box(
               modifier = Modifier
                   .weight(1f)
                   .padding(6.dp)
                   .background(color = Color.White, shape = shapes.small),
               contentAlignment = Alignment.Center,
           )
           {
               Text(
                   text = stringResource(R.string.thirty_min),
                   fontSize = 13.sp,
                   lineHeight = 20.sp,
                   style = typography.h4,
                   fontWeight = FontWeight(400),
                   color = secondaryNavy,
                   textAlign = TextAlign.Center,
               )
           }
           Box(
               modifier = Modifier
                   .weight(1f)
                   .padding(6.dp)
                   .background(color = Color.White, shape = shapes.small)
                   .height(32.dp),
               contentAlignment = Alignment.Center,
           )
           {
               Text(
                   text = stringResource(R.string.sixty_min),
                       fontSize = 13.sp,
                       lineHeight = 20.sp,
                       style = typography.h4,
                       fontWeight = FontWeight(400),
                       color = secondaryNavy,
                       textAlign = TextAlign.Center,
                   )
           }
           Box(
               modifier = Modifier
                   .weight(1f)
                   .padding(6.dp)
                   .background(color = Color.White, shape = shapes.small)
                   .height(32.dp),
               contentAlignment = Alignment.Center,
           )
           {
               Text(
                   text = stringResource(R.string.hour_and_a_half),
                   fontSize = 13.sp,
                   lineHeight = 20.sp,
                   style = typography.h4,
                   fontWeight = FontWeight(400),
                   color = secondaryNavy,
                   textAlign = TextAlign.Center,
               )
           }
           Box(
               modifier = Modifier
                   .weight(1f)
                   .padding(6.dp)
                   .background(color = Color.White, shape = shapes.small)
                   .height(32.dp),
               contentAlignment = Alignment.Center,
           )
           {
               Text(
                   text = stringResource(R.string.two_hours),
                   fontSize = 13.sp,
                   lineHeight = 20.sp,
                   style = typography.h4,
                   fontWeight = FontWeight(400),
                   color = secondaryNavy,
                   textAlign = TextAlign.Center,
               )
           }
        }
    }
}