package com.vodafone.wheather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityPreferenceDao {
    @Query("SELECT * FROM city_preferences LIMIT 1")
    suspend fun getLastSearchedCity(): CityPreference?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(cityPreference: CityPreference)
}