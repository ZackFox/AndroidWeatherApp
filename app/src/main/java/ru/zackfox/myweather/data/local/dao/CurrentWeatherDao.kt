package ru.zackfox.myweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.zackfox.myweather.data.local.entity.CurrentEntity

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persist (current: CurrentEntity)

    @Query("select * from current_weather where id = 0")
    suspend fun getLast (): CurrentEntity
}