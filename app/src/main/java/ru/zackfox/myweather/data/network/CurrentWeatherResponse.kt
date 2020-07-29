package ru.zackfox.myweather.data.network

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(val data : List<CurrentEntry>)

@Entity(tableName = "weather_current")
data class CurrentEntry(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("city_name") val cityName: String,
    @SerializedName("country_code") val countryСode: String,
    @SerializedName("temp") val temp: Double,
    @SerializedName("app_temp") val feelsLike: Double,
    @SerializedName("pres") val pressure: Double,
    @SerializedName("rh") val humidity: Int,
    @SerializedName("pod") val partOfDay: String,
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("vis") val visibility: Int,
    @SerializedName("wind_spd") val windSpeed: Double,
    @SerializedName("wind_cdir_full") val windDirectionFull: String,
    @Embedded(prefix = "weather_") val weather: Weather
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}


