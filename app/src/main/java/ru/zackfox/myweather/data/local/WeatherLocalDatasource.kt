package ru.zackfox.myweather.data.local

import ru.zackfox.myweather.data.local.entity.CurrentEntity

interface WeatherLocalDatasource {
    suspend fun getLastCurrentWeather (): CurrentEntity

    suspend fun persistCurrentWeather ( currentEntity: CurrentEntity)
}
