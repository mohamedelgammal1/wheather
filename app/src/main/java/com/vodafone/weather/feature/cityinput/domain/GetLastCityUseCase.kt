package com.vodafone.weather.feature.cityinput.domain

import com.vodafone.weather.data.repository.WeatherRepository
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(): String? {
        return weatherRepository.getLastSearchedCity()
    }
}