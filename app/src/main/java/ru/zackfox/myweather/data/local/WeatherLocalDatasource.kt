package ru.zackfox.myweather.data.local

import ru.zackfox.myweather.data.local.entity.CurrentEntity
import ru.zackfox.myweather.data.local.entity.DailyEntity
import ru.zackfox.myweather.data.local.entity.LocationEntity

interface WeatherLocalDatasource {
    suspend fun getLastCurrentWeather (): CurrentEntity

    suspend fun persistCurrentWeather ( currentEntity: CurrentEntity)

    suspend fun getLastDailyWeather (): List<DailyEntity>

    suspend fun persistDailytWeather ( list: List<DailyEntity>)

    suspend fun getLastLocation (): LocationEntity

    suspend fun persistLocation ( location: LocationEntity)
}
