package com.patrik.meteorite.data.source.network

import com.patrik.meteorite.api.ApiService
import javax.inject.Inject

class MeteoriteNetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loadMeteorites(): List<NetworkMeteorite> {
            return apiService.getMeteorites()
    }
}