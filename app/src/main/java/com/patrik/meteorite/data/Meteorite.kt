package com.patrik.meteorite.data

import android.location.Location


data class Meteorite(
    val id: Int,
    val mass: Int,
    val name: String,
    val recclass: String,
    val reclat: Double,
    val reclong: Double,
    val date: String,
) {

    val mapTitle = "$name $date ${mass}g"
    val listTitle = "$name $date"
    var distance: Float = Float.MAX_VALUE

    fun calculateDistance(currentLat: Double, currentLong: Double) {
        val results = FloatArray(1)
        try {

            Location.distanceBetween(
                reclat, reclong,
                currentLat, currentLong, results
            )
            distance = results[0]
        } catch (e: Exception) {
            distance = Float.MAX_VALUE
        }
    }
}
