package ru.zackfox.myweather.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "city_name") val cityName: String = "",
    @ColumnInfo(name = "country_code") val countryСode: String = "",
    @ColumnInfo(name = "timezone") val timezone: String = ""
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}