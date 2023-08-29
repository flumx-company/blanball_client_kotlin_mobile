package com.example.blanball.presentation.views.screens.event

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundClassicRed
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.classicBlue
import com.example.blanball.presentation.theme.classicRed
import com.example.blanball.presentation.theme.classicYellow
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.EventBottomButtons
import com.example.blanball.presentation.views.components.cards.AddUserToTeam
import com.example.blanball.presentation.views.components.cards.ConfirmEmailReminder
import com.example.blanball.presentation.views.components.cards.PlayerOnEventCard
import com.example.blanball.presentation.views.components.cards.UserCardWithPhone
import com.example.blanball.presentation.views.components.switches.TeamSwitcher
import com.example.blanball.presentation.views.components.tabrows.TabRow
import com.example.blanball.presentation.views.components.texts.TextBadge

@Composable
fun EventScreen(
    paddingValues: PaddingValues,
    verificationModalScreenContent: @Composable () -> Unit,
    shareLinkModalScreenContent: @Composable () -> Unit,
    isVerificationModalVisible: MutableState<Boolean>,
    isShareLinkModalVisible: MutableState<Boolean>,
) {
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_ball),
        painterResource(id = R.drawable.ic_peoples),
        painterResource(id = R.drawable.ic_field),
        painterResource(id = R.drawable.ic_add_user)
    )
    val tabs: List<String> = listOf(
        stringResource(R.string.users_list),
        stringResource(R.string.coaching_staff),
        stringResource(R.string.registered_viewers),
        stringResource(R.string.requests_for_participation),
    )
    var descriptionTextExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            when {
                isVerificationModalVisible.value -> verificationModalScreenContent()
                isShareLinkModalVisible.value -> shareLinkModalScreenContent()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 0.dp)
        ) {
            ConfirmEmailReminder(
                clickCallback = { isVerificationModalVisible.value = true }
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Змагання на голозабивання",  //TODO()
                fontSize = 20.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Дружній матч",
                fontSize = 13.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(500),
                color = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(22.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    tint = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "16 червня", //TODO()
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "12:00 – 14:00", //TODO()
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Запоріжжя, Центральна, стадіон «Торпеда»", //TODO()
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = null)
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.сost_of_participation),
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Безкоштовно", //TODO()
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = stringResource(R.string.description_of_the_event),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = secondaryNavy,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                modifier = Modifier
                    .animateContentSize()
                    .clickable { descriptionTextExpanded = !descriptionTextExpanded },
                text = "Запрошуємо вас на захоплюючий футбольний матч на Лінкольна 17, Львів! Змагатимуться сильні команди, атмосфера буде неймовірною, а ви зможете насолоджуватися якісною грою та спілкуванням з однодумцями. Участь у події коштує 150 грн, оскільки ми надаємо гравцям та глядачам все необхідне: оренду поля, розважальні активності, екіпірування, організацію та проведення змагання. Реєструйтеся зараз та готуйтеся до спортивного драйву та моря позитиву!",
                fontSize = 14.sp,
                lineHeight = 24.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = primaryDark,
                maxLines = if (descriptionTextExpanded) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TextBadge(textResId = R.string.football) //TODO()
                TextBadge(textResId = R.string.mans) //TODO()
                TextBadge(textResId = R.string.withour_divison) //TODO()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                Modifier
                    .background(color = bgLight, shape = shapes.medium)
                    .padding(
                        start = 12.dp,
                        top = 8.dp,
                        end = 12.dp,
                        bottom = 12.dp,
                    )
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.first_team),
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(600),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            Column {
                                Text(
                                    text = stringResource(R.string.t_shirts),
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(500),
                                    color = secondaryNavy,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = bgItemsGray,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            start = 8.dp,
                                            top = 2.dp,
                                            end = 8.dp,
                                            bottom = 2.dp
                                        )
                                ) {
                                    Row {
                                        Box(
                                            Modifier
                                                .background(
                                                    color = Color.White,
                                                    shape = RoundedCornerShape(2.dp)
                                                )
                                                .padding(2.dp)
                                        ) {
                                            Box(
                                                Modifier
                                                    .size(12.dp)
                                                    .background(color = classicBlue)
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = stringResource(R.string.blue), //TODO()
                                            fontSize = 12.sp,
                                            lineHeight = 20.sp,
                                            style = typography.h4,
                                            fontWeight = FontWeight(500),
                                            color = primaryDark,
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            Column {
                                Text(
                                    text = stringResource(R.string.shorts),
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(500),
                                    color = secondaryNavy,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = bgItemsGray,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            start = 8.dp,
                                            top = 2.dp,
                                            end = 8.dp,
                                            bottom = 2.dp
                                        )
                                ) {
                                    Row {
                                        Box(
                                            Modifier
                                                .background(
                                                    color = Color.White,
                                                    shape = RoundedCornerShape(2.dp)
                                                )
                                                .padding(2.dp)
                                        ) {
                                            Box(
                                                Modifier
                                                    .size(12.dp)
                                                    .background(color = classicRed)
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = stringResource(R.string.red), //TODO()
                                            fontSize = 12.sp,
                                            lineHeight = 20.sp,
                                            style = typography.h4,
                                            fontWeight = FontWeight(500),
                                            color = primaryDark,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Box(
                Modifier
                    .background(color = bgLight, shape = shapes.medium)
                    .padding(
                        start = 12.dp,
                        top = 8.dp,
                        end = 12.dp,
                        bottom = 12.dp,
                    )
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.second_team),
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(600),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            Column {
                                Text(
                                    text = stringResource(R.string.t_shirts),
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(500),
                                    color = secondaryNavy,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = bgItemsGray,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            start = 8.dp,
                                            top = 2.dp,
                                            end = 8.dp,
                                            bottom = 2.dp
                                        )
                                ) {
                                    Row {
                                        Box(
                                            Modifier
                                                .background(
                                                    color = Color.White,
                                                    shape = RoundedCornerShape(2.dp)
                                                )
                                                .padding(2.dp)
                                        ) {
                                            Box(
                                                Modifier
                                                    .size(12.dp)
                                                    .background(color = classicYellow)
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = stringResource(R.string.yellow), //TODO()
                                            fontSize = 12.sp,
                                            lineHeight = 20.sp,
                                            style = typography.h4,
                                            fontWeight = FontWeight(500),
                                            color = primaryDark,
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            Column {
                                Text(
                                    text = stringResource(R.string.shorts),
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(500),
                                    color = secondaryNavy,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = bgItemsGray,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            start = 8.dp,
                                            top = 2.dp,
                                            end = 8.dp,
                                            bottom = 2.dp
                                        )
                                ) {
                                    Row {
                                        Box(
                                            Modifier
                                                .background(
                                                    color = Color.White,
                                                    shape = RoundedCornerShape(2.dp)
                                                )
                                                .padding(2.dp)
                                        ) {
                                            Box(
                                                Modifier
                                                    .size(12.dp)
                                                    .background(color = Color.White)
                                                    .border(
                                                        width = 1.dp,
                                                        shape = RoundedCornerShape(2.dp),
                                                        color = defaultLightGray
                                                    )
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = stringResource(R.string.white), //TODO()
                                            fontSize = 12.sp,
                                            lineHeight = 20.sp,
                                            style = typography.h4,
                                            fontWeight = FontWeight(500),
                                            color = primaryDark,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            UserCardWithPhone(
                userAvatarUrl = "http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg", //TODO()
                userFirstName = "Ceргій",
                userLastName = "Білецький",
                userPhone = "+380 66 873 05 75",
                userRole = "Організатор",
                clickCallback = {},
            )
            Spacer(Modifier.size(12.dp))
            UserCardWithPhone(
                userAvatarUrl = "http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg", //TODO()
                userFirstName = "Ярослав",
                userLastName = "Бойко",
                userPhone = "+380 97 773 05 81",
                userRole = "Тренер",
                clickCallback = {},
            )
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                modifier = Modifier
                    .border(width = 1.dp, color = defaultLightGray, shape = shapes.medium)
                    .fillMaxWidth()
                    .height(160.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.temp_map_image), //TODO()
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(36.dp))
            Text(
                text = stringResource(R.string.already_confirme_participation),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = Color(0xFF262541),
            )
            TabRow(tabs = tabs, icons = icons)
            Spacer(modifier = Modifier.size(20.dp))
            TeamSwitcher(
                "Команда 1",
                "Команда 2" //TODO()
            )
            Spacer(modifier = Modifier.size(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundClassicRed, shape = shapes.medium)
                    .border(width = 1.dp, color = classicRed, shape = shapes.medium)
                    .padding(vertical = 24.dp, horizontal = 12.dp)
            ) {
                Column {
                    repeat(times = 8) {
                        PlayerOnEventCard(
                            userAvatarUrl = "http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg",
                            userFirstName = "Женя",
                            userLastName = "Жучара",
                            userPosition = "ВРТ",
                            userRating = 4.0f,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                    repeat(3) {//TODO()
                        AddUserToTeam()
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.size(58.dp))
        }
        EventBottomButtons(
            toJoinBtnClick = { /*TODO*/ },
            shareBtnClick = { isShareLinkModalVisible.value = true }
        )
    }
}