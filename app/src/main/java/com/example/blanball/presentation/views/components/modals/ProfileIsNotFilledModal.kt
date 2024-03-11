package com.example.blanball.presentation.views.components.modals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shadowDark
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.DefaultButton

@Composable
fun ProfileIsNotFilledModal(
    onNavToProfileEditing: () -> Unit,
    onModalClose: () -> Unit,
){
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(durationMillis = 700)),
        exit = fadeOut(animationSpec = tween(durationMillis = 700))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .width(292.dp)
                    .wrapContentHeight()
                    .shadow(
                        elevation = 10.dp,
                        spotColor = shadowDark,
                        ambientColor = shadowDark
                    )
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    modifier = Modifier.wrapContentSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                )
                {
                    Box(modifier = Modifier.align(Alignment.End)) {
                        Icon(
                            modifier = Modifier.size(28.dp).clickable {
                                onModalClose()
                            },
                            painter = painterResource(R.drawable.ic_cancel),
                            tint = primaryDark,
                            contentDescription = null,
                        )
                    }
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            modifier = Modifier.offset(x = 6.dp),
                            painter = painterResource(id = R.drawable.error_modal),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            )
                        Image(
                            painter = painterResource(id = R.drawable.error_stars),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            )

                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = stringResource(R.string.lacks_info),
                        style = typography.h3,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        color = primaryDark,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        modifier = Modifier
                            .width(260.dp)
                            .wrapContentHeight()
                            .clickable( onClick = {

                            }),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = secondaryNavy,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                )
                            ) {
                                append(stringResource(R.string.filled_profile))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = mainGreen,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    textDecoration = TextDecoration.Underline,
                                )
                            ) {
                                append(stringResource(R.string.sport_info))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = secondaryNavy,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                )
                            ) {
                                append(stringResource(R.string.in_personal_cabinet))
                            }
                        },
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    DefaultButton(
                        btnTextResId = R.string.complete_profile,
                        clickCallback = { onNavToProfileEditing() }
                    )
                }
            }
        }
    }
}