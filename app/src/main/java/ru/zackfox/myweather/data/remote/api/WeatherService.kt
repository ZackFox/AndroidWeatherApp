package ru.zackfox.myweather.data.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zackfox.myweather.BuildConfig
import ru.zackfox.myweather.data.remote.interceptor.ApiKeyInterceptor

object WeatherService {
    val retrofit: WeatherApi

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        retrofit = Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    fun api (): WeatherApi =  retrofit
}