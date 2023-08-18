package com.example.blanball.presentation.views.screens.technicalworks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Composable
fun TechnicalWorksScreen(
// navigateToRegistration: () -> Unit, TODO()
) {
    val techWorksSplittedWords = stringResource(R.string.technical_works).uppercase().split(" ")
    Box {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .offset(y = (32).dp),
            painter = painterResource(id = R.drawable.tech_works_bg),
            contentDescription = null
        )
        Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp)) {

            Text(
                text = AnnotatedString.Builder().apply {
                    for ((index, word) in techWorksSplittedWords.withIndex()) {
                        pushStyle(SpanStyle(color = if (index == 2 || index == 4) primaryDark else secondaryNavy))
                        append("$word ")
                        pop()
                    }
                }.toAnnotatedString(),
                fontSize = 20.sp,
                lineHeight = 28.sp,
                style = typography.h3,
                fontWeight = FontWeight(800),
                color = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = stringResource(R.string.our_team_works),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Divider(color = defaultLightGray)
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = stringResource(id = R.string.dont_have_acc),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(500),
                color = primaryDark,
            )
            Text(
                modifier = Modifier.clickable { }, //TODO
                text = stringResource(R.string.register_and_stay_up),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(500),
                color = mainGreen,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.ic_gears_tech_works),
                contentDescription = null,
            )
        }
    }
}