package com.patrik.meteorite.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.patrik.meteorite.MeteoritesScreenViewState

@Composable
fun MeteoriteListScreen(
    modifier: Modifier = Modifier,
    meteoritesScreenViewState: MeteoritesScreenViewState.MeteoritesList,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    meteoritesScreenViewState.location?.let { location ->
        meteoritesScreenViewState.meteorites.forEach { meteorite ->
            meteorite.calculateDistance(location.latitude, location.longitude)
        }
    }

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = meteoritesScreenViewState.meteorites.sortedBy { it.distance }, key = { viewState -> viewState.id }) { meteorite ->
            MeteoriteListItem(meteorite.getTitle(), { }, modifier)
        }
    }

    meteoritesScreenViewState.message?.let { message ->
        val snackBarText = stringResource(message)
        LaunchedEffect(scaffoldState, meteoritesScreenViewState, message, snackBarText) {
            scaffoldState.snackbarHostState.showSnackbar(snackBarText)
        }
    }
}