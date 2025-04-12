package com.vodafone.wheather.feature.cityinput.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CityInputScreen(
    onSearchClicked: (String) -> Unit,
    viewModel: CityInputViewModel = hiltViewModel()
) {
    val cityName by viewModel.cityName.collectAsState()
    val lastSearchedCity by viewModel.lastSearchedCity.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Weather Now & Later",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = cityName,
            onValueChange = { viewModel.onCityNameChanged(it) },
            label = { Text("Enter City Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                viewModel.saveCity()
                onSearchClicked(cityName)
            },
            enabled = cityName.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        if (lastSearchedCity != null) {
            Text(
                text = "Last searched: $lastSearchedCity",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}