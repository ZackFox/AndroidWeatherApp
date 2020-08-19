package ru.zackfox.myweather.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zackfox.myweather.data.local.WeatherLocalDatasourceImpl
import ru.zackfox.myweather.data.local.entity.CurrentEntity
import ru.zackfox.myweather.data.local.entity.LocationEntity
import ru.zackfox.myweather.data.remote.WeatherRemoteDatasourceImpl
import ru.zackfox.myweather.data.remote.dto.toEntity
import ru.zackfox.myweather.util.NetworkChecker
import ru.zackfox.myweather.util.providers.ILocationProvider
import java.io.IOException
import java.util.*

class WeatherRepositoryImpl (
    val localDatasource: WeatherLocalDatasourceImpl,
    val remoteDatasource: WeatherRemoteDatasourceImpl,
    val locationProvider: ILocationProvider,
    val networkHelper: NetworkChecker
): WeatherRepository {

//    override suspend fun getCurrentWeather(lat: Double,lon: Double,lang: String, units: String ): CurrentEntity {
//
//        return try {
//                if(!networkHelper.isNetworkConnected()){
//                    // from database
//                    withContext(Dispatchers.IO){
//                            localDatasource.getLastCurrentWeather()
//                    }
//                } else {
//                    // from network
//                    withContext(Dispatchers.IO){
//                        val response = remoteDatasource.getCurrentWeather(lat,lon,lang,units)
//                        response.toEntity().also {
//                                localDatasource.persistCurrentWeather(it)
//                            }
//                    }
//                }
//            } catch (e: IOException) {
//                throw Exception("AAAA")
//            }
//    }

    override suspend fun getCurrentWeather(): CurrentEntity {
        return withContext(Dispatchers.IO){
            val location = locationProvider.getLocation()
            remoteDatasource.getCurrentWeather(location.lat, location.lon, Locale.getDefault().language,"M")
                .toEntity()
        }
    }

    override suspend fun getLastLocation(): LocationEntity {
        return withContext(Dispatchers.IO){
            localDatasource.getLastLocation()
        }
    }

    companion object{
        var INSTANCE: WeatherRepository? = null
        val LOCK = Any();

        fun getInstance( localDatasource: WeatherLocalDatasourceImpl,
                         remoteDatasource: WeatherRemoteDatasourceImpl,
                         locationProvider:ILocationProvider,
                         networkHelper: NetworkChecker): WeatherRepository {

            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: WeatherRepositoryImpl(localDatasource,remoteDatasource,locationProvider,networkHelper)
                    .also { INSTANCE = it }
            }
        }
    }
}