package com.vodafone.weather.data.di
import android.content.Context
import androidx.room.Room
import com.vodafone.weather.data.local.CityPreferenceDao
import com.vodafone.weather.data.local.WeatherDatabase
import com.vodafone.weather.data.remote.WeatherApi
import com.vodafone.weather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCityPreferenceDao(database: WeatherDatabase): CityPreferenceDao {
        return database.cityPreferenceDao()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        cityPreferenceDao: CityPreferenceDao
    ): WeatherRepository {
        return WeatherRepository(weatherApi, cityPreferenceDao)
    }
}