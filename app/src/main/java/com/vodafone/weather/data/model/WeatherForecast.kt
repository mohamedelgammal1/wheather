package com.vodafone.weather.data.model

data class WeatherForecast(
    val cityName: String,
    val dailyForecasts: List<ForecastDay>
)
