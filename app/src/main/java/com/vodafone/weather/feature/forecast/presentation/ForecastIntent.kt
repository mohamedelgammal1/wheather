package com.vodafone.weather.feature.forecast.presentation

sealed class ForecastIntent {
    data class LoadForecast(val cityName: String) : ForecastIntent()
}