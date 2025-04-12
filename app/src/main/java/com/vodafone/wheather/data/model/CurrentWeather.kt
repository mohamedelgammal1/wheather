package com.vodafone.wheather.data.model

data class CurrentWeather(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val timestamp: Long
)