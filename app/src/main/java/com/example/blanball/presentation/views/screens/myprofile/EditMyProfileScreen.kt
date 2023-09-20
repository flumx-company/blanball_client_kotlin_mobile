package com.example.blanball.presentation.views.screens.myprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.cards.MyRatingCard
import com.example.blanball.presentation.views.components.tabrows.TabRow
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.texts.MyProfileMainGreenTextBadge

@Composable
fun EditMyProfileScreen(
    state: UiState,
    paddingValues: PaddingValues,
    cancelBtnClicked: () -> Unit,
    saveBtnClicked: () -> Unit,
) {
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_user),
        painterResource(id = R.drawable.ic_rate)
    )

    val tabs: List<String> = listOf(
        stringResource(id = R.string.my_profile),
        stringResource(R.string.rate_plan),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        (state as? MyProfileScreensMainContract.State)?.let {
            Column (modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp))
            {
                Text(
                    text = stringResource(R.string.my_cabinet).uppercase(),
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    style = typography.h2,
                    fontWeight = FontWeight(800),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Оновіть своє фото та персональні дані",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                TabRow(tabs = tabs, icons = icons)
                Spacer(modifier = Modifier.size(20.dp))
                DefaultCardWithColumn {
                    Row {
                        if (state.myAvatarUrl.value.isNullOrEmpty()) {
                            Box(
                                modifier = Modifier.size(56.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.circle_avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .fillMaxSize()
                                )
                                Text(
                                    text = "${state.myLastNameText.value.firstOrNull() ?: ""} ${state.myFirstNameText.value.firstOrNull() ?: ""}",
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    ),
                                    style = typography.h2, fontSize = 56.sp, color = mainGreen
                                )
                            }
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(state.myAvatarUrl.value),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Column {
                            Text(
                                text = "Стефанія Калиновська", //TODO()
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(700),
                                color = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Row {
                                MyProfileMainGreenTextBadge(text = "Гравець")
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(text = "25 років")
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(text = "Жінка")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                MyRatingCard()
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.about_me),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.your_lastname,
                    state = it ,
                    value = it.myLastNameText.value,
                    onValueChange = { state.myLastNameText.value} ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.your_firstname ,
                    state = it,
                    value = it.myFirstNameText.value,
                    onValueChange = { state.myFirstNameText.value = it } ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId =R.string.a_few_words_about_me,
                    state = it ,
                    value = it.aboutMeText.value,
                    onValueChange = {state.aboutMeText.value = it} ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}