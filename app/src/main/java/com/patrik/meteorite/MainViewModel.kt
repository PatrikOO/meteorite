package com.patrik.meteorite

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrik.meteorite.data.Meteorite
import com.patrik.meteorite.data.MeteoriteRepository
import com.patrik.meteorite.util.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class MeteoritesScreenViewState {
    data object Loading : MeteoritesScreenViewState()
    data class MeteoritesList(
        val meteorites: List<Meteorite> = emptyList(),
        val location: Location? = null,
        val message: String? = null
    ) : MeteoritesScreenViewState()
}


@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val meteoriteRepository: MeteoriteRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    var currentLocation by mutableStateOf<Location?>(null)

    fun getCurrentLocation() {
        viewModelScope.launch {
            currentLocation = locationTracker.getCurrentLocation()
            Timber.d("Location ${currentLocation.toString()}")
        }
    }

    val meteoritesScreenViewState =
        meteoriteRepository.meteorites.map { meteorites ->
            if (meteorites.isEmpty()) {
                MeteoritesScreenViewState.Loading
            } else {
                MeteoritesScreenViewState.MeteoritesList(
                    meteorites = meteorites,
                    location = currentLocation,
                    message = ""
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MeteoritesScreenViewState.Loading
        )

    init {
        viewModelScope.launch {
            meteoriteRepository.loadMeteorites()
        }
    }
}