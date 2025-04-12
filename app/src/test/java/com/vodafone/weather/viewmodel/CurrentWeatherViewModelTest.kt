package com.vodafone.weather.viewmodel


import com.vodafone.weather.data.model.CurrentWeather
import com.vodafone.weather.feature.currentweather.domain.GetCurrentWeatherUseCase
import com.vodafone.weather.feature.currentweather.presentation.CurrentWeatherUiState
import com.vodafone.weather.feature.currentweather.presentation.CurrentWeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCurrentWeatherUseCase = mock(GetCurrentWeatherUseCase::class.java)
        viewModel = CurrentWeatherViewModel(getCurrentWeatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadWeatherForCity sets state to Loading then Success on successful use case`() = runTest {
        // Given
        val cityName = "London"
        val currentWeather = CurrentWeather(
            cityName = "London",
            temperature = 15.0,
            condition = "Cloudy",
            timestamp = 1617123456
        )

        `when`(getCurrentWeatherUseCase(cityName)).thenReturn(flowOf(Result.success(currentWeather)))

        // When
        viewModel.loadWeatherForCity(cityName)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(CurrentWeatherUiState.Success(currentWeather), viewModel.uiState.value)
    }

    @Test
    fun `loadWeatherForCity sets state to Loading then Error on failed use case`() = runTest {
        // Given
        val cityName = "InvalidCity"
        val errorMessage = "City not found"

        `when`(getCurrentWeatherUseCase(cityName)).thenReturn(flowOf(Result.failure(Exception(errorMessage))))

        // When
        viewModel.loadWeatherForCity(cityName)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(CurrentWeatherUiState.Error(errorMessage), viewModel.uiState.value)
    }
}