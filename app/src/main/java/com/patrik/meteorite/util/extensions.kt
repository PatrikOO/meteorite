package com.patrik.meteorite.util

import com.patrik.meteorite.AppConfig
import java.math.RoundingMode

fun String.toTimeDateLong(): Long {
    return try {
        AppConfig.METEORITE_NETWORK_SDF.parse(this)?.time ?: 0
    } catch (e: Exception) {
        0
    }
}

fun String.toTimeDateLongOrNull(): Long? {
    return try {
        AppConfig.METEORITE_NETWORK_SDF.parse(this)?.time
    } catch (e: Exception) {
        null
    }
}

fun Long.toUiDate(): String {
    return try {
        return AppConfig.METEORITE_EXTERNAL_SDF.format(this)
    } catch (e: Exception) {
        "-"
    }

}

fun Int.asWeight(): String {
    return if (this < 1000) {
        "${this}g"
    } else {
        "${this.toBigDecimal().divide(1000.toBigDecimal(), 0, RoundingMode.HALF_UP)}kg"
    }
}

