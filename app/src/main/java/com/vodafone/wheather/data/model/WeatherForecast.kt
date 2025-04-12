package com.vodafone.wheather.data.model

data class WeatherForecast(
    val cityName: String,
    val dailyForecasts: List<ForecastDay>
)
