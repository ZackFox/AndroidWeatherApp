package ru.zackfox.myweather.util.providers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.gms.location.*
import ru.zackfox.myweather.data.local.entity.LocationEntity
import ru.zackfox.myweather.util.asDeferred
import java.util.*

const val CUSTOM_LOCATION = "CUSTOM_LOCATION"
const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"


class LocationProvider (private val context: Context, val locationClient: FusedLocationProviderClient): ILocationProvider {

    private lateinit var location: LocationEntity


    val preferences : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    val locationManager: LocationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager

    val geocoder = Geocoder(context, Locale.getDefault())

//    override val gps_enabled
//        get() = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//
//    override val network_enabled
//        get() = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    override suspend fun hasLocationChanged(): Boolean{
        return false
    }

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): LocationEntity {
        return if(isUsingDeviceLocation()){
            val location = locationClient.lastLocation.asDeferred().await()
            val data = geocoder.getFromLocation(location.latitude , location.longitude,1)[0]
            Log.d("LP", "device = ${data.getAddressLine(0)} ")
            LocationEntity(location.latitude, location.longitude, data.getAddressLine(0), data.countryCode)
        } else {
            val data = geocoder.getFromLocationName(getCustomLocation(),1)[0]
            Log.d("LP", "custom = ${data.getAddressLine(0)} ")
            LocationEntity(data.latitude,data.longitude, data.getAddressLine(0),data.countryCode)
        }
    }

    private fun hasCustomLocationChanged(locationEntity: LocationEntity): Boolean {
        if (!isUsingDeviceLocation()) {
            val customLocationName = getCustomLocation()
            return customLocationName != locationEntity.cityName
        }
        return false
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation () : Location? {
        return locationClient.lastLocation.result
    }

    private fun getCustomLocation (): String? {
        return preferences.getString(CUSTOM_LOCATION,null)
    }

    private fun isUsingDeviceLocation(): Boolean {
        return preferences.getBoolean(USE_DEVICE_LOCATION, true)
    }
}