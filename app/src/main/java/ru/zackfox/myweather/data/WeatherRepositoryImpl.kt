package ru.zackfox.myweather.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zackfox.myweather.data.local.WeatherLocalDatasourceImpl
import ru.zackfox.myweather.data.local.entity.CurrentEntity
import ru.zackfox.myweather.data.remote.WeatherRemoteDatasourceImpl
import ru.zackfox.myweather.data.remote.dto.toEntity
import ru.zackfox.myweather.util.NetworkChecker
import java.io.IOException

class WeatherRepositoryImpl (
    val localDatasource: WeatherLocalDatasourceImpl,
    val remoteDatasource: WeatherRemoteDatasourceImpl,
    val networkHelper: NetworkChecker
): WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double,lon: Double,lang: String, units: String ): CurrentEntity {

        return try {
                if(!networkHelper.isNetworkConnected()){
                    // from database
                    withContext(Dispatchers.IO){
                        localDatasource.getLastCurrentWeather()
                    }
                } else {
                    // from network
                    withContext(Dispatchers.IO){
                        remoteDatasource.getCurrentWeather(lat,lon,lang,units).toEntity().also {
                            localDatasource.persistCurrentWeather(it)
                        }
                    }
                }
            } catch (e: IOException) {
                throw Exception()
            }
    }
}