package com.patrik.meteorite

import LocationTracker
import android.location.Location
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.patrik.meteorite.data.Meteorite
import com.patrik.meteorite.data.MeteoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch
import javax.inject.Inject

data class MeteoritesUiState(
    val items: List<Meteorite> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val meteoriteRepository: MeteoriteRepository,
    //private val locationTracker: LocationTracker
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    var currentLocation by mutableStateOf<Location?>(null)

//    fun getCurrentLocation() {
//        viewModelScope.launch {
//            currentLocation = locationTracker.getCurrentLocation()
//        }
//    }



    val uiState: StateFlow<List<Meteorite>> = meteoriteRepository
        .observeMeteorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    init {
        viewModelScope.launch {
            refresh()
        }
    }


    fun refresh() {
        _isLoading.value = true
        viewModelScope.launch {
            meteoriteRepository.refresh()
            _isLoading.value = false
        }
    }

}