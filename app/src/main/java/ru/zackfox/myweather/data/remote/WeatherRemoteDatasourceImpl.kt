package ru.zackfox.myweather.data.remote

import ru.zackfox.myweather.data.remote.dto.CurrentEntryDto
import ru.zackfox.myweather.data.remote.api.WeatherService


class WeatherRemoteDatasourceImpl (val weatherService: WeatherService) : WeatherRemoteDatasource {
    override suspend fun getCurrentWeather (lat: Double, lon: Double, lang: String, units: String): CurrentEntryDto {
        return weatherService.api().getCurrentWeather(lat, lon, lang, units).data[0];
    }

    companion object{
        var INSTANCE: WeatherRemoteDatasource? = null
        val LOCK = Any();

        fun getInstance(weatherService: WeatherService): WeatherRemoteDatasource {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: WeatherRemoteDatasourceImpl(weatherService)
                    .also { INSTANCE = it }
            }
        }
    }
}
