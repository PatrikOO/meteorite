package com.patrik.meteorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrik.meteorite.data.MeteoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val meteoriteRepository: MeteoriteRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            meteoriteRepository.refresh();
        }
    }

}