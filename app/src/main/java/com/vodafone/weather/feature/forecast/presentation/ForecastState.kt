package com.vodafone.weather.feature.forecast.presentation

import com.vodafone.weather.data.model.WeatherForecast

data class ForecastState(
    val isLoading: Boolean = false,
    val forecast: WeatherForecast? = null,
    val error: String? = null
)