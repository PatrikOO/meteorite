package com.patrik.meteorite.api

import com.patrik.meteorite.data.source.network.NetworkMeteorite
import retrofit2.http.GET

interface ApiService {
    @GET("gh4g-9sfh.json?\$\$app_token=wL0ehFiTiRWxxSKrlqihYcaYp")
    suspend fun getMeteorites(): List<NetworkMeteorite>
}

open class ApiException(message: String, exception: Exception) : Exception(message, exception)