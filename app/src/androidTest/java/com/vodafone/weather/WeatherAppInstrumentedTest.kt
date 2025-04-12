package com.vodafone.weather

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.vodafone.weather.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class WeatherAppInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun cityInputScreen_enterCity_navigatesToWeatherScreen() {
        // Given
        composeTestRule.onNodeWithText("Enter City Name").assertIsDisplayed()

        // When
        composeTestRule.onNodeWithText("Enter City Name").performTextInput("London")
        composeTestRule.onNodeWithText("Search").performClick()

        // Then
        composeTestRule.onNodeWithText("London").assertIsDisplayed()
        composeTestRule.onNodeWithText("Current Weather").assertIsDisplayed()
        composeTestRule.onNodeWithText("7-Day Forecast").assertIsDisplayed()
    }

    @Test
    fun weatherTabs_switchBetweenCurrentWeatherAndForecast() {
        // Given
        composeTestRule.onNodeWithText("Enter City Name").performTextInput("Paris")
        composeTestRule.onNodeWithText("Search").performClick()

        // When - Current Weather tab
        composeTestRule.onNodeWithText("Current Weather").performClick()

        // Then
        composeTestRule.onNodeWithText("Paris").assertIsDisplayed()

        // When - Switch to Forecast tab
        composeTestRule.onNodeWithText("7-Day Forecast").performClick()

        // Then
        composeTestRule.onNodeWithText("7-Day Forecast for Paris").assertIsDisplayed()
    }
}