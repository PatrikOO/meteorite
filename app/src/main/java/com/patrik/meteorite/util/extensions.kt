package com.patrik.meteorite.util

import com.patrik.meteorite.AppConfig

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