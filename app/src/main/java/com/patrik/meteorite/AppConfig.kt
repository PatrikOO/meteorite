package com.patrik.meteorite

import java.text.SimpleDateFormat
import java.util.Locale

object AppConfig {
    const val BASE_URL: String = "https://data.nasa.gov/resource/"
    const val METEORITE_NETWORK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    val METEORITE_NETWORK_SDF = SimpleDateFormat(METEORITE_NETWORK_DATE_FORMAT, Locale.US)

    const val METEORITE_EXTERNAL_DATE_FORMAT = "dd.MM.yyyy"
    val METEORITE_EXTERNAL_SDF = SimpleDateFormat(METEORITE_EXTERNAL_DATE_FORMAT, Locale.US)

    const val NO_DATA = "-"
    const val FROM_DATE = 1293836400000L
}
