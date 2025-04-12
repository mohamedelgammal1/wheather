package com.vodafone.weather.feature.currentweather.presentation

import com.vodafone.weather.data.model.CurrentWeather

sealed class CurrentWeatherUiState {
    object Initial : CurrentWeatherUiState()
    object Loading : CurrentWeatherUiState()
    data class Success(val data: CurrentWeather) : CurrentWeatherUiState()
    data class Error(val message: String) : CurrentWeatherUiState()
}