package com.example.blanball.presentation.views.screens.selectlocation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.SelectLocationScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.maps.SelectLocationWithGoogleMap
import com.example.blanball.utils.ext.getAddressFromLocation
import com.google.android.gms.maps.model.LatLng

@Composable
fun SelectLocationScreen(
    onCancelClicked: () -> Unit,
    onSaveLocationClicked: (lanLat: LatLng) -> Unit,
    state: UiState,
    onUpdateCitiesForRegionList: () -> Unit,
    onUpdateMap: () -> Unit
) {
    val context = LocalContext.current
    (state as? SelectLocationScreenMainContract.State)?.let { currentState ->

        Box(
            modifier = Modifier.padding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
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
                    listItems = currentState.regionOfUkraineList.value,
                    value = currentState.selectedRegion.value,
                    onValueChange = {
                        currentState.selectedRegion.value = it
                        onUpdateCitiesForRegionList()
                    }
                )
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    labelResId = R.string.city_village_town,
                    listItems = currentState.citiesForRegionList.value,
                    value = currentState.selectedCity.value,
                    onValueChange = {
                        currentState.selectedCity.value = it
                        onUpdateMap()
                    }
                )

                Spacer(modifier = Modifier.size(12.dp))
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.clickable {
                            currentState.isMapExpanded.value = !currentState.isMapExpanded.value
                        },
                        text = if (currentState.isMapExpanded.value) stringResource(R.string.close_the_map) else stringResource(
                            R.string.open_the_map
                        ),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = mainGreen,
                        textDecoration = TextDecoration.Underline,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.hold_your_finger_to_select_a_loc),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                SelectLocationWithGoogleMap(
                    eventLocationLatLng = currentState.selectedCityLatLan,
                    height = 244.dp,
                    isMapExtended = currentState.isMapExpanded.value,
                    isMarkerVisible = true,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = currentState.selectedCityLatLan.value.getAddressFromLocation(context)
                        ?: "",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(shape = shapes.small)
                            .clickable { onCancelClicked() }
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                            .wrapContentHeight()
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
                        modifier = Modifier
                            .background(
                                color = mainGreen,
                                shape = shapes.small
                            )
                            .clickable { onSaveLocationClicked(currentState.selectedCityLatLan.value) }
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                            .wrapContentHeight(),
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
            if (currentState.state == SelectLocationScreenMainContract.ScreenViewState.Loading){
                Loader()
            }
        }
    }
}