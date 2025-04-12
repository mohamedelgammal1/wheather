package com.vodafone.weather.feature.forecast.domain


import com.vodafone.weather.data.model.WeatherForecast
import com.vodafone.weather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Flow<Result<WeatherForecast>> {
        return weatherRepository.getWeatherForecast(cityName)
    }
}