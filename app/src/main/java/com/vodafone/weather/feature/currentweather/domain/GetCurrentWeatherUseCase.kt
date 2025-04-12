package com.vodafone.weather.feature.currentweather.domain


import com.vodafone.weather.data.model.CurrentWeather
import com.vodafone.weather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Flow<Result<CurrentWeather>> {
        return weatherRepository.getCurrentWeather(cityName)
    }
}