package com.vodafone.wheather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_preferences")
data class CityPreference(
    @PrimaryKey val id: Int = 1, // Single row for last city
    val cityName: String
)