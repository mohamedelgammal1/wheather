package com.vodafone.weather.feature.forecast.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.vodafone.weather.core.ui.ErrorView
import com.vodafone.weather.core.ui.LoadingIndicator
import com.vodafone.weather.data.model.ForecastDay
import com.vodafone.weather.data.model.WeatherForecast
import com.vodafone.weatherutils.DateTimeFormatter
import com.vodafone.weatherutils.TemperatureFormatter
import com.vodafone.weatherutils.WeatherIconMapper


@Composable
fun ForecastScreen(
    cityName: String,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityName) {
        viewModel.processIntent(ForecastIntent.LoadForecast(cityName))
    }

    when {
        state.isLoading -> LoadingIndicator()
        state.error != null -> ErrorView(state.error!!)
        state.forecast != null -> ForecastContent(state.forecast!!)
    }
}

@Composable
fun ForecastContent(forecast: WeatherForecast) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "7-Day Forecast for ${forecast.cityName}",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(forecast.dailyForecasts) { day ->
                ForecastDayItem(day)
                Divider()
            }
        }
    }
}

@Composable
fun ForecastDayItem(day: ForecastDay) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = DateTimeFormatter.getDayOfWeek(day.timestamp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = day.condition,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        val condition = WeatherIconMapper.mapApiConditionToEnum(day.condition)
        val iconResource = WeatherIconMapper.getIconResourceForCondition(condition)

        // In a real app, use actual icons
        Text(
            text = iconResource,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = TemperatureFormatter.formatCelsius(day.temperature),
            style = MaterialTheme.typography.titleLarge
        )
    }
}