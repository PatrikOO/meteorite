package com.patrik.meteorite.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.maps.android.compose.GoogleMap
import com.patrik.meteorite.MeteoritesScreenViewState

@Composable
fun MeteoriteMapScreen(
    modifier: Modifier = Modifier,
    meteoritesScreenViewState: MeteoritesScreenViewState.MeteoritesList,
    scaffoldState: ScaffoldState = rememberScaffoldState()
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

    meteoritesScreenViewState.message?.let { message ->
        val snackBarText = stringResource(message)
        LaunchedEffect(scaffoldState, meteoritesScreenViewState, message, snackBarText) {
            scaffoldState.snackbarHostState.showSnackbar(snackBarText)
        }
    }
}