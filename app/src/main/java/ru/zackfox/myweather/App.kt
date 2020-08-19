package ru.zackfox.myweather

import android.app.Application
import androidx.preference.PreferenceManager
import com.google.android.gms.location.LocationServices
import ru.zackfox.myweather.data.WeatherRepository
import ru.zackfox.myweather.data.WeatherRepositoryImpl
import ru.zackfox.myweather.data.local.WeatherDb
import ru.zackfox.myweather.data.local.WeatherLocalDatasourceImpl
import ru.zackfox.myweather.data.remote.WeatherRemoteDatasourceImpl
import ru.zackfox.myweather.data.remote.api.WeatherService
import ru.zackfox.myweather.util.NetworkHelper
import ru.zackfox.myweather.util.providers.LocationProvider

class App :Application() {
    val locationClient
        get() = LocationServices.getFusedLocationProviderClient(this)

    val locationProvider
        get() = LocationProvider(this, locationClient)

    val repository: WeatherRepository
        get() = run {
            val appDatabase = WeatherDb.getInstance(this)

            return  WeatherRepositoryImpl.getInstance(
                WeatherLocalDatasourceImpl.getInstance(appDatabase) as WeatherLocalDatasourceImpl,
                WeatherRemoteDatasourceImpl.getInstance(WeatherService) as WeatherRemoteDatasourceImpl,
                locationProvider,
                NetworkHelper(this)
            )
        }

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.settings, false)

    }
}

