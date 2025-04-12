package com.vodafone.weather.feature.currentweather.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vodafone.weatherutils.DateTimeFormatter
import com.vodafone.weatherutils.TemperatureFormatter
import com.vodafone.weatherutils.WeatherIconMapper
import com.vodafone.weather.core.ui.ErrorView
import com.vodafone.weather.core.ui.LoadingIndicator
import com.vodafone.weather.data.model.CurrentWeather


@Composable
fun CurrentWeatherScreen(
    cityName: String,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(cityName) {
        viewModel.loadWeatherForCity(cityName)
    }

    when (val state = uiState) {
        is CurrentWeatherUiState.Initial -> {}
        is CurrentWeatherUiState.Loading -> LoadingIndicator()
        is CurrentWeatherUiState.Success -> CurrentWeatherContent(state.data)
        is CurrentWeatherUiState.Error -> ErrorView(state.message)
    }
}

@Composable
fun CurrentWeatherContent(weather: CurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.cityName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = DateTimeFormatter.formatDate(weather.timestamp),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val condition = WeatherIconMapper.mapApiConditionToEnum(weather.condition)
        val iconResource = WeatherIconMapper.getIconResourceForCondition(condition)

        // Display weather icon (in a real app, use actual icons)
        Text(
            text = "Icon: $iconResource",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = TemperatureFormatter.formatCelsius(weather.temperature),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = weather.condition,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}