package ru.zackfox.myweather.data.remote

import ru.zackfox.myweather.data.remote.dto.CurrentEntryDto
import ru.zackfox.myweather.data.remote.api.WeatherService


class WeatherRemoteDatasourceImpl (val weatherService: WeatherService) : WeatherRemoteDatasource {
    override suspend fun getCurrentWeather (lat: Double, lon: Double, lang: String, units: String): CurrentEntryDto {
        return weatherService.api().getCurrentWeather(lat, lon, lang, units).data.get(0);
    }
}
