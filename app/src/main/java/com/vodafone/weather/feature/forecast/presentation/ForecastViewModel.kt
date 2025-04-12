package com.vodafone.weather.feature.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.weather.feature.forecast.domain.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForecastState())
    val state: StateFlow<ForecastState> = _state

    fun processIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.LoadForecast -> loadForecast(intent.cityName)
        }
    }

    private fun loadForecast(cityName: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            getForecastUseCase(cityName).collect { result ->
                result.fold(
                    onSuccess = { forecast ->
                        _state.value = _state.value.copy(
                            isLoading = false,
                            forecast = forecast,
                            error = null
                        )
                    },
                    onFailure = { e ->
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = e.message ?: "Unknown error occurred"
                        )
                    }
                )
            }
        }
    }
}