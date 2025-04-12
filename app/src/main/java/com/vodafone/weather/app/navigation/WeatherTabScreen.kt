package com.vodafone.weather.app.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.vodafone.weather.feature.currentweather.presentation.CurrentWeatherScreen
import com.vodafone.weather.feature.forecast.presentation.ForecastScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTabScreen(
    cityName: String,
    onBackClick: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Current Weather", "7-Day Forecast")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = cityName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            when (tabIndex) {
                0 -> CurrentWeatherScreen(cityName)
                1 -> ForecastScreen(cityName)
            }
        }
    }
}