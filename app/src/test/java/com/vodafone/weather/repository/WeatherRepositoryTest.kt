package com.vodafone.weather.repository
import com.vodafone.weather.data.local.CityPreference
import com.vodafone.weather.data.local.CityPreferenceDao
import com.vodafone.weather.data.remote.CurrentWeatherResponse
import com.vodafone.weather.data.remote.MainInfo
import com.vodafone.weather.data.remote.WeatherApi
import com.vodafone.weather.data.remote.WeatherInfo
import com.vodafone.weather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class WeatherRepositoryTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var cityPreferenceDao: CityPreferenceDao
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        weatherApi = mock(WeatherApi::class.java)
        cityPreferenceDao = mock(CityPreferenceDao::class.java)
        repository = WeatherRepository(weatherApi, cityPreferenceDao)
    }

    @Test
    fun `getCurrentWeather returns success result when API call is successful`() = runBlocking {
        // Given
        val cityName = "London"
        val mockResponse = CurrentWeatherResponse(
            name = "London",
            main = MainInfo(temp = 15.0),
            weather = listOf(WeatherInfo(main = "Cloudy", description = "overcast clouds")),
            dt = 1617123456
        )

        `when`(weatherApi.getCurrentWeather(eq(cityName), any(), any())).thenReturn(mockResponse)

        // When
        val result = repository.getCurrentWeather(cityName).first()

        // Then
        assertTrue(result.isSuccess)
        val weather = result.getOrNull()!!
        assertEquals("London", weather.cityName)
        assertEquals(15.0, weather.temperature, 0.001)
        assertEquals("Cloudy", weather.condition)
    }

    @Test
    fun `getCurrentWeather returns failure result when API call fails`() = runBlocking {
        // Given
        val cityName = "InvalidCity"
        `when`(weatherApi.getCurrentWeather(eq(cityName), any(), any())).thenThrow(RuntimeException("Network error"))

        // When
        val result = repository.getCurrentWeather(cityName).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getLastSearchedCity returns saved city name`() = runBlocking {
        // Given
        val cityPreference = CityPreference(cityName = "Paris")
        `when`(cityPreferenceDao.getLastSearchedCity()).thenReturn(cityPreference)

        // When
        val result = repository.getLastSearchedCity()

        // Then
        assertEquals("Paris", result)
    }

    @Test
    fun `saveLastSearchedCity calls dao to save city`() = runBlocking {
        // Given
        val cityName = "Berlin"

        // When
        repository.saveLastSearchedCity(cityName)

        // Then
        verify(cityPreferenceDao).saveCity(CityPreference(cityName = cityName))
    }
}