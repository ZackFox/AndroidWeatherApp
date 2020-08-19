package ru.zackfox.myweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.zackfox.myweather.data.local.entity.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persist (location: LocationEntity)

    @Query("select * from location")
    suspend fun getLast (): LocationEntity
}