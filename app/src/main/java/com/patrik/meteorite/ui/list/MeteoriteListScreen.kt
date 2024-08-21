package com.patrik.meteorite.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrik.meteorite.MeteoritesScreenViewState
import kotlinx.coroutines.CoroutineScope

@Composable
fun MeteoriteListScreen(
    modifier: Modifier = Modifier,
    meteoritesScreenViewState: MeteoritesScreenViewState.MeteoritesList
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
}