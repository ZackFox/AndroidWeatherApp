package ru.zackfox.myweather.data

import ru.zackfox.myweather.data.local.entity.CurrentEntity
import ru.zackfox.myweather.data.local.entity.LocationEntity

interface WeatherRepository {
    suspend fun getCurrentWeather(): CurrentEntity
    suspend fun getLastLocation(): LocationEntity
}