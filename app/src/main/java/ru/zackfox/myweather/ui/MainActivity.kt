package ru.zackfox.myweather.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import ru.zackfox.myweather.R
import ru.zackfox.myweather.data.local.WeatherLocalDatasourceImpl
import ru.zackfox.myweather.data.remote.WeatherRemoteDatasourceImpl
import ru.zackfox.myweather.data.WeatherRepositoryImpl
import ru.zackfox.myweather.data.local.WeatherDb
import ru.zackfox.myweather.data.remote.api.WeatherService
import ru.zackfox.myweather.util.NetworkHelper

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(
            WeatherRepositoryImpl(
                WeatherLocalDatasourceImpl(
                    WeatherDb.getInstance(this.applicationContext)
                ),
                WeatherRemoteDatasourceImpl(
                    WeatherService
                ),
                NetworkHelper(this.applicationContext)
            )
        )
    
        // get data from network || database
        mainViewModel.getCurrentWeather(53.41, 59.04, "ru", "M")

        // show loading
//        mainViewModel.loading.observe(this, Observer{
//            if (it) {
//                loading.visibility = View.VISIBLE
//                home_text.visibility = View.GONE
//            }
//            else {
//                loading.visibility = View.GONE
//                home_text.visibility = View.VISIBLE
//            }
//        })

        // show data
        mainViewModel.currentWeather.observe(this, Observer {
            if(it == null) return@Observer
            loading.visibility = View.GONE
            updateLocationTitle(it.cityName,"Сегодня")
            updateTemperature(it.temperature.toInt().toString(), "M")

            GlideApp
                .with(this)
                .load("https://www.weatherbit.io/static/img/icons/${it.weatherIcon}.png")
                .into(weather_icon)
        })
    }

    private fun updateLocationTitle(location: String, date: String){
        topToolBar.title = location
        topToolBar.subtitle = date
    }

    private fun updateTemperature(temperature: String, units: String){
        val _units = if (units == "M") "°C" else "°F"
        view_temperature.text = "$temperature $_units"
    }
}