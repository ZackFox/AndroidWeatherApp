package ru.zackfox.myweather.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zackfox.myweather.BuildConfig

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

    fun api (): WeatherApi = this.retrofit
}