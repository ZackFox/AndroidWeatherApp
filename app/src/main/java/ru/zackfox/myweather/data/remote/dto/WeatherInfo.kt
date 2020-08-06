package ru.zackfox.myweather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("icon") val icon: String = "",
    @SerializedName("code") val code: Int = 0,
    @SerializedName("description") val description: String = ""
)