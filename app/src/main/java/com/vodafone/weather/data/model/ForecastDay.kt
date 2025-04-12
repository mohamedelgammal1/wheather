package com.vodafone.weather.data.model

data class ForecastDay(
    val timestamp: Long,
    val temperature: Double,
    val condition: String
)