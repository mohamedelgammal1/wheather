package com.vodafone.weather.usecase

import com.vodafone.weather.data.model.CurrentWeather
import com.vodafone.weather.data.repository.WeatherRepository
import com.vodafone.weather.feature.currentweather.domain.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetCurrentWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        repository = mock(WeatherRepository::class.java)
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke calls repository and returns its result`() = runBlocking {
        // Given
        val cityName = "London"
        val mockWeather = CurrentWeather(
            cityName = "London",
            temperature = 15.0,
            condition = "Cloudy",
            timestamp = 1617123456
        )
        val expectedResult = Result.success(mockWeather)

        `when`(repository.getCurrentWeather(cityName)).thenReturn(flowOf(expectedResult))

        // When
        val result = useCase(cityName).first()

        // Then
        verify(repository).getCurrentWeather(cityName)
        assertEquals(expectedResult, result)
    }
}