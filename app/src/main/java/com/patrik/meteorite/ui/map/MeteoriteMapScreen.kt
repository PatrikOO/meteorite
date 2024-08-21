package com.patrik.meteorite.ui.map

import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.GoogleMap
import com.patrik.meteorite.MainViewModel
import com.patrik.meteorite.MeteoritesScreenViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MeteoriteMapScreen(
    modifier: Modifier = Modifier,
    meteoritesScreenViewState: MeteoritesScreenViewState.MeteoritesList
) {
    var isMapLoaded by remember { mutableStateOf(false) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        onMapLoaded = { isMapLoaded = true }
    ) {

        MeteoriteMarkers(
            meteorites = meteoritesScreenViewState.meteorites,
        )
    }
}