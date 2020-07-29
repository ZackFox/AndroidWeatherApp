package ru.zackfox.myweather.data.network;

import kotlinx.coroutines.Deferred;
import retrofit2.http.GET;
import retrofit2.http.Query

interface WeatherApi {

    @GET("current")
    fun getCurrentWeather(
        @Query("city") city: String = "Magnitogorsk",
        @Query("lang") lang: String = "ru"
    ): Deferred<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("lon") lon: Double = 59.05,
        @Query("lat") lat: Double = 53.42,
        @Query("lang") lang: String = "ru"
    ): Deferred<CurrentWeatherResponse>

    @GET("forecast/daily")
    fun getDailyWeather(
        @Query("city") city: String = "Magnitogorsk",
        @Query("lang") lang: String = "ru"
    ): Deferred<DailyWeatherDto>

    @GET("forecast/daily")
    fun getDailyWeather(
        @Query("lon") lon: Double = 59.05,
        @Query("lat") lat: Double = 53.42,
        @Query("lang") lang: String = "ru"
    ): Deferred<DailyWeatherDto>
}
