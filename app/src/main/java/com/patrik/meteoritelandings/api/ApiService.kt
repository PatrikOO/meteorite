package com.patrik.meteoritelandings.api

import com.patrik.meteoritelandings.data.source.network.NetworkMeteorite
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getMeteorites(): List<NetworkMeteorite>
}

open class ApiException(message: String, exception: Exception) : Exception(message, exception)