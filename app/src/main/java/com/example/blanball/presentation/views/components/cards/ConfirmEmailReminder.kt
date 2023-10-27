package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun ConfirmEmailReminder(
    clickCallback: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = primaryDark, shape = shapes.medium)
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth() ) {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.confirm_your_email) + "\n"+ "zhenyazhuck@outlook.com", //TODO()
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(500),
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Box (  modifier = Modifier
                .height(28.dp)
                .background(color = secondaryNavy, shape = shapes.small)
                .clickable{ clickCallback() }
                .padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                        fontSize = 13.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
            }
        }
    }
}