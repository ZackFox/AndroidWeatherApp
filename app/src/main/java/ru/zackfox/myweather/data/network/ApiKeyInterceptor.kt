package ru.zackfox.myweather.data.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.zackfox.myweather.BuildConfig

class ApiKeyInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val withApiKey = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(withApiKey)
            .build()

        return chain.proceed(request)
    }
}