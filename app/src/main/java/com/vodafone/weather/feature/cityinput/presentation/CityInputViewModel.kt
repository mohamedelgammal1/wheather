package com.vodafone.weather.feature.cityinput.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.weather.feature.cityinput.domain.GetLastCityUseCase
import com.vodafone.weather.feature.cityinput.domain.SaveCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val getLastCityUseCase: GetLastCityUseCase,
    private val saveCityUseCase: SaveCityUseCase
) : ViewModel() {

    private val _cityName = MutableStateFlow("")
    val cityName: StateFlow<String> = _cityName

    private val _lastSearchedCity = MutableStateFlow<String?>(null)
    val lastSearchedCity: StateFlow<String?> = _lastSearchedCity

    init {
        loadLastSearchedCity()
    }

    fun onCityNameChanged(newName: String) {
        _cityName.value = newName
    }

    fun saveCity() {
        viewModelScope.launch {
            if (_cityName.value.isNotBlank()) {
                saveCityUseCase(_cityName.value)
                _lastSearchedCity.value = _cityName.value
            }
        }
    }

    private fun loadLastSearchedCity() {
        viewModelScope.launch {
            _lastSearchedCity.value = getLastCityUseCase()
            if (_lastSearchedCity.value != null) {
                _cityName.value = _lastSearchedCity.value!!
            }
        }
    }
}