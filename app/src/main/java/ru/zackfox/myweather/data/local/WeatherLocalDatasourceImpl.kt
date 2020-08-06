package ru.zackfox.myweather.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.zackfox.myweather.data.local.entity.CurrentEntity

class WeatherLocalDatasourceImpl (private val appDatabase: WeatherDb) : WeatherLocalDatasource {
    override suspend fun getLastCurrentWeather(): CurrentEntity {
        return appDatabase.getCurrentDao().getLast()
    }

    override suspend fun persistCurrentWeather(currentEntity: CurrentEntity) {
        appDatabase.getCurrentDao().persist(currentEntity)
    }
}
