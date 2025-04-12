package com.vodafone.wheather.library.weatherutils

import java.text.DecimalFormat

object TemperatureFormatter {
    private val formatter = DecimalFormat("#.#")

    fun formatCelsius(temp: Double): String {
        return "${formatter.format(temp)}°C"
    }

    fun formatFahrenheit(temp: Double): String {
        return "${formatter.format(temp)}°F"
    }

    fun celsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9/5) + 32
    }

    fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5/9
    }
}