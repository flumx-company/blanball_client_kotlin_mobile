package com.example.blanball.presentation.views.components.maps

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.example.blanball.presentation.theme.shapes
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun SelectLocationWithGoogleMap(
    eventLocationLatLng: MutableState<LatLng>,
    onSelectLocationScreenNav: () -> Unit = {},
    height: Dp,
    isMapExtended: Boolean = false,
    isMarkerVisible: Boolean = false,
    isClickable: Boolean = true,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(eventLocationLatLng.value, 10f)
    }
    LaunchedEffect(eventLocationLatLng.value) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(eventLocationLatLng.value, 10f)
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (isMapExtended) Modifier.fillMaxSize() else Modifier.height(height))
            .clip(shape = shapes.medium)
            .animateContentSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = {
            if (isClickable) {
                onSelectLocationScreenNav()
            }
        },
        onMapLongClick = { latLng ->
            eventLocationLatLng.value = latLng
        },
    ) {
        if (isMarkerVisible) {
            Marker(
                state = MarkerState(position = eventLocationLatLng.value),
            )
        }
    }
}
