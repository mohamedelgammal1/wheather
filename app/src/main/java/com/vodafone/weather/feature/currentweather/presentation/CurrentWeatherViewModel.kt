package com.vodafone.weather.feature.currentweather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.weather.feature.currentweather.domain.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CurrentWeatherUiState>(CurrentWeatherUiState.Initial)
    val uiState: StateFlow<CurrentWeatherUiState> = _uiState

    fun loadWeatherForCity(cityName: String) {
        viewModelScope.launch {
            _uiState.value = CurrentWeatherUiState.Loading

            getCurrentWeatherUseCase(cityName)
                .catch { e ->
                    _uiState.value = CurrentWeatherUiState.Error(e.message ?: "Unknown error")
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { currentWeather ->
                            _uiState.value = CurrentWeatherUiState.Success(currentWeather)
                        },
                        onFailure = { e ->
                            _uiState.value = CurrentWeatherUiState.Error(e.message ?: "Unknown error")
                        }
                    )
                }
        }
    }
}