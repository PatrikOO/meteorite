package com.patrik.meteorite.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrik.meteorite.MainViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MeteoriteScreen(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: MainViewModel = hiltViewModel()
) {

    Text(
        text = "Meteorite",
        modifier = modifier
    )
}