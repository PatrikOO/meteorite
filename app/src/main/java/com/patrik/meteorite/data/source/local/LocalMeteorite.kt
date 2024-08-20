package com.patrik.meteorite.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.patrik.meteorite.AppConfig.NO_DATA
import com.patrik.meteorite.data.Meteorite
import com.patrik.meteorite.data.source.network.NetworkMeteorite
import com.patrik.meteorite.util.toTimeDateLongOrNull
import com.patrik.meteorite.util.toUiDate
import kotlin.math.roundToInt

@Entity(
    tableName = "meteorite"
)
data class LocalMeteorite(
    @PrimaryKey val id: Int,
    val mass: Int,
    val name: String,
    val recclass: String,
    val reclat: Double,
    val reclong: Double,
    val timestamp: Long?,
)


fun LocalMeteorite.toExternal() = Meteorite(
    id = id,
    mass = mass,
    name = name,
    recclass = recclass,
    reclat = reclat,
    reclong = reclong,
    date = timestamp?.toUiDate() ?: NO_DATA,
)

fun List<LocalMeteorite>.toExternal() = map(LocalMeteorite::toExternal)

fun NetworkMeteorite.toLocal() = LocalMeteorite(
    id = id!!.toInt(),
    mass = mass?.toDoubleOrNull()?.roundToInt() ?: Int.MIN_VALUE,
    name = name ?: NO_DATA,
    recclass = recclass ?: NO_DATA,
    reclat = reclat!!.toDouble(),
    reclong = reclong!!.toDouble(),
    timestamp = year?.toTimeDateLongOrNull()
)

fun List<NetworkMeteorite>.toLocal() = map(NetworkMeteorite::toLocal)

