package com.vodafone.weather.data.repository

import com.vodafone.weather.data.local.CityPreference
import com.vodafone.weather.data.local.CityPreferenceDao
import com.vodafone.weather.data.model.CurrentWeather
import com.vodafone.weather.data.model.ForecastDay
import com.vodafone.weather.data.model.WeatherForecast
import com.vodafone.weather.data.remote.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityPreferenceDao: CityPreferenceDao
) {
    private val apiKey = "YOUR_API_KEY" // In a real app, store this securely

    suspend fun getCurrentWeather(cityName: String): Flow<Result<CurrentWeather>> = flow {
        try {
            val response = weatherApi.getCurrentWeather(cityName, apiKey = apiKey)
            val currentWeather = CurrentWeather(
                cityName = response.name,
                temperature = response.main.temp,
                condition = response.weather.firstOrNull()?.main ?: "Unknown",
                timestamp = response.dt
            )
            emit(Result.success(currentWeather))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun getWeatherForecast(cityName: String): Flow<Result<WeatherForecast>> = flow {
        try {
            val response = weatherApi.getWeatherForecast(cityName, apiKey = apiKey)
            val forecast = WeatherForecast(
                cityName = response.city.name,
                dailyForecasts = response.list.map { daily ->
                    ForecastDay(
                        timestamp = daily.dt,
                        temperature = daily.temp.day,
                        condition = daily.weather.firstOrNull()?.main ?: "Unknown"
                    )
                }
            )
            emit(Result.success(forecast))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun saveLastSearchedCity(cityName: String) {
        cityPreferenceDao.saveCity(CityPreference(cityName = cityName))
    }

    suspend fun getLastSearchedCity(): String? {
        return cityPreferenceDao.getLastSearchedCity()?.cityName
    }
}