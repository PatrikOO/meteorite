package com.patrik.meteorite.data


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
}
