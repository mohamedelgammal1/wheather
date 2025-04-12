package com.vodafone.wheather.data.remote

data class CurrentWeatherResponse(
    val name: String,
    val main: MainInfo,
    val weather: List<WeatherInfo>,
    val dt: Long
)

data class MainInfo(
    val temp: Double
)

data class WeatherInfo(
    val main: String,
    val description: String
)