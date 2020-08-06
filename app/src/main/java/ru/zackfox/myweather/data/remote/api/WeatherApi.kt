package ru.zackfox.myweather.data.remote.api;

import kotlinx.coroutines.Deferred;
import retrofit2.http.GET;
import retrofit2.http.Query
import ru.zackfox.myweather.data.remote.dto.CurrentWeatherResponse
import ru.zackfox.myweather.data.remote.dto.DailyWeatherDto

interface WeatherApi {

    @GET("current")
    suspend fun getCurrentWeather(
        @Query("city") city: String = "Magnitogorsk",
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "M"
    ): CurrentWeatherResponse

    @GET("current")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "M"
    ): CurrentWeatherResponse

    @GET("forecast/daily")
    suspend fun getDailyWeather(
        @Query("city") city: String = "Magnitogorsk",
        @Query("lang") lang: String = "ru"
    ): DailyWeatherDto

    @GET("forecast/daily")
    suspend fun getDailyWeather(
        @Query("lon") lon: Double = 59.05,
        @Query("lat") lat: Double = 53.42,
        @Query("lang") lang: String = "ru"
    ): DailyWeatherDto
}
