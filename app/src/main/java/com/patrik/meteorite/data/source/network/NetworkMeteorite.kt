package com.patrik.meteorite.data.source.network

import com.patrik.meteorite.util.toTimeDateLongOrNull

class NetworkMeteorite(
    val id: String?,
    val mass: String?,
    val name: String?,
    val recclass: String?,
    val reclat: String?,
    val reclong: String?,
    val year: String?
) {


    fun isValid(): Boolean {
        return (id?.toIntOrNull() != null
                && reclat?.toDoubleOrNull() != null
                && reclong?.toDoubleOrNull() != null
                && year?.toTimeDateLongOrNull() != null)
    }
}