package com.vodafone.wheather.feature.cityinput.domain

import com.vodafone.wheather.data.repository.WeatherRepository
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(): String? {
        return weatherRepository.getLastSearchedCity()
    }
}