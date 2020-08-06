package ru.zackfox.myweather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import ru.zackfox.myweather.data.local.dao.CurrentWeatherDao
import ru.zackfox.myweather.data.local.entity.CurrentEntity

@Database(entities = [CurrentEntity::class], version = 1)
abstract class WeatherDb : RoomDatabase(){
    abstract fun getCurrentDao(): CurrentWeatherDao

    companion object{
        private var instance: WeatherDb? = null
        private val LOCK = Any()

        fun getInstance(context: Context): WeatherDb {
            return instance ?:
                synchronized(LOCK) {
                    instance ?: databaseBuilder(context.applicationContext,
                            WeatherDb::class.java, "app_database")
                            .build().also { instance = it }
                }
        }
    }
}