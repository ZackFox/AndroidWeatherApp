package ru.zackfox.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.zackfox.myweather.data.network.WeatherService

class MainActivity : AppCompatActivity() {
    val TAG = "API"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherService = WeatherService.api()
        GlobalScope.launch(Dispatchers.Main) {
            val response = weatherService.getCurrentWeather().await();
            Log.d(TAG, response.toString())
        }
    }
}
