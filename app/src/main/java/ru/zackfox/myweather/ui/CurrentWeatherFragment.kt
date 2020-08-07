package ru.zackfox.myweather.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_current_weather.*
import ru.zackfox.myweather.R
import ru.zackfox.myweather.data.WeatherRepositoryImpl
import ru.zackfox.myweather.data.local.WeatherDb
import ru.zackfox.myweather.data.local.WeatherLocalDatasourceImpl
import ru.zackfox.myweather.data.remote.WeatherRemoteDatasourceImpl
import ru.zackfox.myweather.data.remote.api.WeatherService
import ru.zackfox.myweather.util.NetworkHelper

class CurrentWeatherFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel(
            WeatherRepositoryImpl(
                WeatherLocalDatasourceImpl(
                    WeatherDb.getInstance((activity as AppCompatActivity).applicationContext)
                ),
                WeatherRemoteDatasourceImpl(
                    WeatherService
                ),
                NetworkHelper((activity as AppCompatActivity).applicationContext)
            )
        )

        mainViewModel = MainViewModel(
            WeatherRepositoryImpl(
                WeatherLocalDatasourceImpl(
                    WeatherDb.getInstance((activity as AppCompatActivity).applicationContext)
                ),
                WeatherRemoteDatasourceImpl(
                    WeatherService
                ),
                NetworkHelper((activity as AppCompatActivity).applicationContext)
            )
        )

        mainViewModel.getCurrentWeather(53.41, 59.04, "ru", "M")

        // show data
        mainViewModel.currentWeather.observe((activity as AppCompatActivity), Observer {
            if(it == null) return@Observer
            Log.d("VM", "$it")
            loading.visibility = View.GONE
            updateLocationTitle(it.cityName,"Сегодня")
            updateTemperature(it.temperature.toInt().toString(), "M")

            GlideApp
                .with(this)
                .load("https://www.weatherbit.io/static/img/icons/${it.weatherIcon}.png")
                .into(weather_icon)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)



        // get data from network || database

    }

    private fun updateLocationTitle(location: String, date: String){
        (activity as AppCompatActivity).topToolBar?.title = location
        (activity as AppCompatActivity).topToolBar?.subtitle = date
    }

    private fun updateTemperature(temperature: String, units: String){
        val _units = if (units == "M") "°C" else "°F"
        view_temperature.text = "$temperature $_units"
    }
}