package ru.zackfox.myweather.data.local

import ru.zackfox.myweather.data.local.entity.CurrentEntity
import ru.zackfox.myweather.data.local.entity.DailyEntity
import ru.zackfox.myweather.data.local.entity.LocationEntity

class WeatherLocalDatasourceImpl (private val appDatabase: WeatherDb) : WeatherLocalDatasource {
    override suspend fun getLastCurrentWeather(): CurrentEntity {
        return appDatabase.getCurrentDao().getLast()
    }

    override suspend fun persistCurrentWeather(currentEntity: CurrentEntity) {
        appDatabase.getCurrentDao().persist(currentEntity)
    }

    // =============================

    override suspend fun getLastDailyWeather(): List<DailyEntity> {
        return appDatabase.getDailyDao().getLast()
    }

    override suspend fun persistDailytWeather(list: List<DailyEntity>) {
        appDatabase.getDailyDao().persist(list)
    }

    override suspend fun getLastLocation(): LocationEntity {
        return appDatabase.getLocationDao().getLast()
    }

    // =============================

    override suspend fun persistLocation(location: LocationEntity) {
        appDatabase.getLocationDao().persist(location)
    }


    companion object{
        var INSTANCE: WeatherLocalDatasource? = null
        val LOCK = Any();

        fun getInstance(appDatabase: WeatherDb): WeatherLocalDatasource {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: WeatherLocalDatasourceImpl(appDatabase)
                    .also { INSTANCE = it }
            }
        }
    }
}
