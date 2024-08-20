package com.patrik.meteorite.ui.map

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.patrik.meteorite.R
import com.patrik.meteorite.data.Meteorite
import com.patrik.meteorite.util.BitmapParameters
import com.patrik.meteorite.util.vectorToBitmap

/**
 * [GoogleMapComposable] which renders a [meteorites] as a set of basic [Marker]s
 */
@Composable
@GoogleMapComposable
fun MeteoriteMarkers(
    meteorites: List<Meteorite>,
    onMeteoriteClick: (Marker) -> Boolean = { false }
) {

    val meteoriteIcon = vectorToBitmap(
        LocalContext.current,
        BitmapParameters(
            id = R.drawable.meteorite,
            iconColor = MaterialTheme.colorScheme.secondary.toArgb(),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer.toArgb(),
        )
    )

    meteorites.forEach { meteorite ->
        Marker(
            state = rememberMarkerState(position = LatLng(meteorite.reclat, meteorite.reclong)),
            title = meteorite.getTitle(),
            anchor = Offset(0.5f, 0.5f),
            icon = meteoriteIcon,
            onClick = { marker ->
                onMeteoriteClick(marker)
                false
            },
        )
    }
}
