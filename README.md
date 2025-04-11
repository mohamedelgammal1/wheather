# ☀️ Weather Now & Later

A modular, clean-architecture Android application that fetches and displays current weather and a 7-day forecast for a given city using **Jetpack Compose**, **Hilt**, **MVVM/MVI**, and more.

---

## 📲 Features

- 🔍 Input a city name to get weather info
- 🌤 Shows current weather with temperature, condition, and icon
- 📆 Displays a 7-day weather forecast in a list
- 💾 Saves the last searched city and auto-loads it on app reopen
- 🌓 Supports dark mode

---

## 🏗 Architecture & Tech Stack

### 🧠 Patterns & Principles
- MVVM for:
  - City input
  - Current weather display
- MVI for:
  - 7-day forecast list
- Clean Architecture with proper separation:
  - Presentation
  - Domain (Use Cases)
  - Data (Repositories + Sources)
- SOLID Principles & Clean Code

### 🔌 Dependency Injection
- Hilt for all DI needs

### 🧱 Modularization
- `:app` - Main application module
- `:core` - Shared utilities and base components
- `:data` - Remote & local data sources and repository
- `:features:cityinput` - City search input UI
- `:features:currentweather` - Current weather display
- `:features:forecast` - 7-day forecast UI
- `:weather-utils-lib` - Custom library for formatting weather info/icons

### 🧪 Testing
- ✅ 80%+ unit test coverage
- 🧪 Mocking with MockK / Mockito
- 📱 Instrumented UI tests

---

## 🛠 Libraries Used

- Kotlin & Coroutines
- Jetpack Compose
- Hilt
- Retrofit + Moshi
- Room (for local storage)
- Coil (for loading weather icons)
- Jetpack Navigation
- GitHub Actions (for CI/CD)
- OpenWeatherMap API

---

## 🧪 CI/CD

Integrated with **GitHub Actions** for:
- ✅ Linting
- 🔍 Running unit tests
- 🏗 Building the APK

---

## 🌐 API Used

**[OpenWeatherMap API](https://openweathermap.org/api)**  
→ Provides both current weather data and 7-day forecasts.  
→ Requires free API key registration.

