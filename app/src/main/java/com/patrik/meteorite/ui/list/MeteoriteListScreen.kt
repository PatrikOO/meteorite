package com.patrik.meteorite.ui.list

import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrik.meteorite.MeteoritesScreenViewState
import com.patrik.meteorite.data.Meteorite


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
            MeteoriteListItem(modifier, meteorite.getTitle(), { })
        }
    }

    meteoritesScreenViewState.message?.let { message ->
        val snackBarText = stringResource(message)
        LaunchedEffect(scaffoldState, meteoritesScreenViewState, message, snackBarText) {
            scaffoldState.snackbarHostState.showSnackbar(snackBarText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeteoriteListScreenPreview() {
    val loc = Location(LocationManager.GPS_PROVIDER)
    loc.latitude = 49.22315
    loc.longitude = 18.73941

    val meteo = listOf(
        Meteorite(
            id = 1,
            mass = 100,
            name = "meteo1",
            recclass = "xx",
            reclat = 49.2,
            reclong = 18.7,
            date = "22.7.1982 18:00"
        ),
        Meteorite(
            id = 2,
            mass = 22,
            name = "meteo2",
            recclass = "xx",
            reclat = 49.25,
            reclong = 18.8,
            date = "1.8.2000 22:00"
        )
    )

    MeteoriteListScreen(Modifier, MeteoritesScreenViewState.MeteoritesList(meteo, loc))
}


