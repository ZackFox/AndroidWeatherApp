package ru.zackfox.myweather.data.remote.dto

import com.google.gson.annotations.SerializedName
import ru.zackfox.myweather.data.local.entity.CurrentEntity

data class CurrentWeatherResponse(val data : List<CurrentEntryDto>)

data class CurrentEntryDto(
    @SerializedName("lat") val lat: Double = 0.0,
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("city_name") val cityName: String = "",
    @SerializedName("country_code") val countryСode: String = "",
    @SerializedName("timezone") val timezone: String = "",
    @SerializedName("temp") val temperature: Double = 0.0,
    @SerializedName("app_temp") val feelsLike: Double = 0.0,
    @SerializedName("pres") val pressure: Double = 0.0,
    @SerializedName("rh") val humidity: Double = 0.0,
    @SerializedName("pod") val partOfDay: String = "",
    @SerializedName("clouds") val clouds: Int = 0,
    @SerializedName("vis") val visibility: Double = 0.0,
    @SerializedName("wind_spd") val windSpeed: Double = 0.0,
    @SerializedName("wind_cdir_full") val windDirectionFull: String = "",
    val weather: WeatherInfo = WeatherInfo()
)

fun CurrentEntryDto.toEntity(): CurrentEntity {
    return CurrentEntity(
        this.lat,
        this.lon,
        this.cityName,
        this.countryСode,
        this.timezone,
        this.temperature,
        this.feelsLike,
        this.pressure,
        this.humidity,
        this.partOfDay,
        this.clouds,
        this.visibility,
        this.windSpeed,
        this.windDirectionFull,
        this.weather.icon,
        this.weather.code,
        this.weather.description
    )
}