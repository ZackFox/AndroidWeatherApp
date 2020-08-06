package ru.zackfox.myweather.data.remote

import ru.zackfox.myweather.data.remote.dto.CurrentEntryDto

interface WeatherRemoteDatasource {
    suspend fun getCurrentWeather (lat: Double, lon: Double, lang: String, units: String): CurrentEntryDto
}
