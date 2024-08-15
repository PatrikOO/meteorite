package com.patrik.meteoritelandings.data


data class Meteorite(
    val id: Int,
    val mass: Int,
    val name: String,
    val recclass: String,
    val reclat: Double,
    val reclong: Double,
    val timestamp: Long,
)