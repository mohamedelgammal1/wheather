package com.vodafone.weather.data.remote

data class ForecastResponse(
    val city: CityInfo,
    val list: List<DailyForecast>
)

data class CityInfo(
    val name: String
)

data class DailyForecast(
    val dt: Long,
    val temp: TempInfo,
    val weather: List<WeatherInfo>
)

data class TempInfo(
    val day: Double
)