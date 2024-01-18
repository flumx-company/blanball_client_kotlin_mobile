package com.example.blanball.presentation.views.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.maps.SelectLocationWithGoogleMap
import com.google.android.gms.maps.model.LatLng

@Composable
fun SelectLocationScreen(
    onCancelClicked: () -> Unit,
    onSaveLocationClicked: () -> Unit,
    eventLocationLatLng: MutableState<LatLng>,
    listOfUkraineRegions: List<String>,
    listOfUkraineCities: List<String>,
    selectRegion: MutableState<String>,
    selectCity: MutableState<String>,
) {
    val isMapExpanded = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.padding()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.location),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(16.dp))
            CustomDropDownMenu(
                labelResId = R.string.region,
                listItems = listOfUkraineRegions,
                value = selectRegion.value,
                onValueChange = { selectRegion.value = it }
            )
            Spacer(modifier = Modifier.size(16.dp))
            CustomDropDownMenu(
                labelResId = R.string.city_village_town,
                listItems = listOfUkraineCities,
                value = selectCity.value,
                onValueChange = { selectCity.value = it }
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = stringResource(R.string.open_the_map),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = mainGreen,
                textDecoration = TextDecoration.Underline,
            )
            Spacer(modifier = Modifier.size(12.dp))
            SelectLocationWithGoogleMap(
                eventLocationLatLng = eventLocationLatLng,
                height = 244.dp,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 12.dp)
                        .clip(shape = shapes.small)
                        .clickable { onCancelClicked() }
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 12.dp)
                        .background(
                            color = mainGreen,
                            shape = shapes.small
                        )
                        .clickable { onSaveLocationClicked() },
                ) {
                    Text(
                        text = stringResource(R.string.save_the_location),
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}