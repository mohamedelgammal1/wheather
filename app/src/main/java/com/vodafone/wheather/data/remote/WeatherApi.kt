package com.vodafone.wheather.data.remote
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): CurrentWeatherResponse

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("cnt") count: Int = 7,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): ForecastResponse
}