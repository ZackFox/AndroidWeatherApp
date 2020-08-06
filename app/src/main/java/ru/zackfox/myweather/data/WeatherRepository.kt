package ru.zackfox.myweather.data

import ru.zackfox.myweather.data.local.entity.CurrentEntity

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double, lang: String, units: String): CurrentEntity
}