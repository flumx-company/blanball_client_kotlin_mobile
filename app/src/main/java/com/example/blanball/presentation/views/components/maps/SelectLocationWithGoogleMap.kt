package com.example.blanball.presentation.views.components.maps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.theme.shapes
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun SelectLocationWithGoogleMapPreview(
    eventLocationLatLng: MutableState<LatLng>,
    onSelectLocationScreenNav: () -> Unit,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(eventLocationLatLng.value, 10f)
    }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(124.dp)
                .clip(shape = shapes.medium),
            cameraPositionState = cameraPositionState,
            onMapClick = { onSelectLocationScreenNav() },
        ) {
            Marker(
                state = MarkerState(position = eventLocationLatLng.value),
            )
        }
}

@Composable
fun SelectLocationWithGoogleMap(
    eventLocationLatLng: MutableState<LatLng>,
    height: Dp,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(eventLocationLatLng.value, 10f)
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape = shapes.medium),
        cameraPositionState = cameraPositionState,
        onMapLongClick = { latLng ->
            eventLocationLatLng.value = latLng
        },
    ) {
        Marker(
            state = MarkerState(position = eventLocationLatLng.value),
        )
    }
}