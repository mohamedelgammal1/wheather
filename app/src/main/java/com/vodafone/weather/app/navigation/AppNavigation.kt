package com.vodafone.weather.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vodafone.weather.feature.cityinput.presentation.CityInputScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    var currentCity by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "cityInput") {
        composable("cityInput") {
            CityInputScreen(
                onSearchClicked = { city ->
                    currentCity = city
                    navController.navigate("weather")
                }
            )
        }

        composable("weather") {
            WeatherTabScreen(
                cityName = currentCity,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}