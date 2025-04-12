package com.vodafone.weatherutils
enum class WeatherCondition {
    SUNNY, CLOUDY, RAINY, SNOWY, STORMY, WINDY, FOGGY, UNKNOWN
}

object WeatherIconMapper {
    fun getIconResourceForCondition(condition: WeatherCondition): String {
        return when (condition) {
            WeatherCondition.SUNNY -> "ic_sunny"
            WeatherCondition.CLOUDY -> "ic_cloudy"
            WeatherCondition.RAINY -> "ic_rainy"
            WeatherCondition.SNOWY -> "ic_snowy"
            WeatherCondition.STORMY -> "ic_stormy"
            WeatherCondition.WINDY -> "ic_windy"
            WeatherCondition.FOGGY -> "ic_foggy"
            WeatherCondition.UNKNOWN -> "ic_unknown"
        }
    }
    fun mapApiConditionToEnum(apiCondition: String): WeatherCondition {
        return when (apiCondition.lowercase()) {
            "clear", "sunny" -> WeatherCondition.SUNNY
            "clouds", "cloudy", "partly cloudy" -> WeatherCondition.CLOUDY
            "rain", "drizzle", "shower rain" -> WeatherCondition.RAINY
            "snow" -> WeatherCondition.SNOWY
            "thunderstorm" -> WeatherCondition.STORMY
            "mist", "fog" -> WeatherCondition.FOGGY
            else -> WeatherCondition.UNKNOWN
        }
    }
}