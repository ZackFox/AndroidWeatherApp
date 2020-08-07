package ru.zackfox.myweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment(CurrentWeatherFragment());

        // navigation selection
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.current_weather -> {
                    setupFragment(CurrentWeatherFragment())
                    true
                }
                R.id.daily_weather -> {
                    setupFragment(DailyWeatherFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFragment(fragment: Fragment): Int {
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}