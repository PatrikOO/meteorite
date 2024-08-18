package com.patrik.meteorite.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrik.meteorite.MainViewModel
import com.patrik.meteorite.data.Meteorite
import kotlinx.coroutines.CoroutineScope

@Composable
fun MeteoriteListScreen(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: MainViewModel = hiltViewModel()
) {

    val screenViewState = viewModel.uiState.collectAsState()
    val viewState = screenViewState.value

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = viewState) { meteorite ->
            MeteoriteListItem(meteorite.id, meteorite.listTitle, { }, modifier)
        }
    }

}