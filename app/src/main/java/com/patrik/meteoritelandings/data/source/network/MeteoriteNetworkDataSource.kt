package com.patrik.meteoritelandings.data.source.network

import com.patrik.meteoritelandings.api.ApiException
import com.patrik.meteoritelandings.api.ApiService
import javax.inject.Inject

class MeteoriteNetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loadMeteorites(): List<NetworkMeteorite> {

        try {
            return apiService.getMeteorites()
        } catch (e: Exception) {
            throw ApiException("Get meteorites failed!", e)
        }
    }

}