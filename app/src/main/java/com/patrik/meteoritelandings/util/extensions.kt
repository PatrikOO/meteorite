package com.patrik.meteoritelandings.util

import com.patrik.meteoritelandings.AppConfig
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toTimeDateLong(): Long {
    val format = SimpleDateFormat(AppConfig.METEORITE_DATE_FORMAT, Locale.US)
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid time string $this")
}