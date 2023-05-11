package com.example.blanball.presentation.views.screens.publicprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.DarkOverlay
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun PublicProfileScreen(
    state: UiState,
    onInviteToAnEventClicked: () -> Unit,
    onWriteToEmailClicked: () -> Unit,
    onCallToUserClicked: () -> Unit,
) {
    val currentState: PublicProfileMainContract.State =
        (state as? PublicProfileMainContract.State) ?: PublicProfileMainContract.State(
            PublicProfileMainContract.ScreenViewState.Idle
        )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? PublicProfileMainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.public_profile_cover),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column() {
            Card(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            ) {
                Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.test_user_photo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(144.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Мирославcький", style = typography.h2, fontSize = 20.sp,
                            color = primaryDark
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Всеволод",
                            style = typography.h2, fontSize = 20.sp,
                            color = primaryDark,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = "Гравець",
                            style = typography.h5,
                            color = secondaryNavy,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.qualification),
                            style = typography.h6, //TODO(color)
                            color = annotationGray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start,
                        )
                        Box(modifier = Modifier
                            .background(color = backgroundItems, shape = shapes.small)
                            .wrapContentWidth()) {
                        Text(
                            text = stringResource(id = R.string.verifed),
                            style = typography.h6,
                            color = primaryDark,
                            textAlign = TextAlign.Start,
                        )
                            Spacer(modifier = Modifier.size(4.dp))
                    }
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Button(
                        onClick = { onInviteToAnEventClicked },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        shape = shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = primaryDark,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.invite_to_an_event),
                            style = typography.h5,
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Row() {
                        Button(
                            onClick = { onInviteToAnEventClicked },
                            modifier = Modifier
                                .height(45.dp)
                                .weight(1f),
                            shape = shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = secondaryNavy,
                            ),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.mail_ic),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = stringResource(id = R.string.write_email),
                                style = typography.h6,
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Button(
                            onClick = { onCallToUserClicked },
                            modifier = Modifier
                                .height(45.dp)
                                .weight(1f),
                            shape = shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = secondaryNavy,
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.phone_ic),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = stringResource(id = R.string.to_call),
                                style = typography.h6,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = stringResource(id = R.string.about_youself),
                        style = typography.h5,
                        color = annotationGray
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Я - футболіст-аматор зі стажем 3 роки. Граю в атакуючій лінії та можу підтримувати гру на високому темпі. Високий рівень фізичної підготовки та хороші комунікативні навички. Шукаю запрошення на матчі в Львові.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        style = typography.h5,
                        color = primaryDark
                    )
                }
            }
            Card(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            ) {
                Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)) {
                    Text(
                        text = stringResource(id = R.string.game_stats), style = typography.h2, fontSize = 16.sp,
                        color = primaryDark
                    )
                    Icon(painter = painterResource(id = R.drawable.empty_star), contentDescription = null)
                    Spacer(modifier = Modifier.size(28.dp))
                    Row() {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color = bgLight, shape = shapes.medium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_flag),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Column() {
                            Text(
                                text = stringResource(id = R.string.game_position),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(text = "ПНЗ", style = typography.h5, color = primaryDark)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color = bgLight, shape = shapes.medium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_dumbbell),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Column() {
                            Text(
                                text = stringResource(id = R.string.weight),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(text = "48 кг", style = typography.h5, color = primaryDark)
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row() {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color = bgLight, shape = shapes.medium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_leg),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Column() {
                            Text(
                                text = stringResource(id = R.string.kicking_leg),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(text = "Права", style = typography.h5, color = primaryDark)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color = bgLight, shape = shapes.medium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_ruler),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Column() {
                            Text(
                                text = stringResource(id = R.string.height),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(text = "168 см", style = typography.h5, color = primaryDark)
                        }
                    }
                }
            }
            Card(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            ) {
                Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)){
                    Row() {
                        Text(
                            text = stringResource(id = R.string.ratings_and_reviews), style = typography.h2,
                            color = primaryDark, fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomEnd) {
                            Row() {
                                Text(text = "4.7", style = typography.subtitle2, color = primaryDark, )
                                Text(text = stringResource(id = R.string.five_scores))
                                Icon(painter = painterResource(id = R.drawable.empty_star), contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}