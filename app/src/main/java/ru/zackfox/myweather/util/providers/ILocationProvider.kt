package ru.zackfox.myweather.util.providers

import android.location.Location
import ru.zackfox.myweather.data.local.entity.LocationEntity

interface ILocationProvider {
//    val gps_enabled: Boolean
//    val network_enabled: Boolean

    suspend fun hasLocationChanged(): Boolean
    suspend fun getLocation() : LocationEntity
}