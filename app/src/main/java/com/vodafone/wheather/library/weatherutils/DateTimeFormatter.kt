package com.vodafone.wheather.library.weatherutils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {
    fun formatDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        return formatter.format(date)
    }

    fun getDayOfWeek(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("EEEE", Locale.getDefault())
        return formatter.format(date)
    }
}