package ru.zackfox.myweather.ui.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_current_weather.*
import ru.zackfox.myweather.R
import ru.zackfox.myweather.ui.GlideApp
import ru.zackfox.myweather.ui.MainActivity
import ru.zackfox.myweather.ui.MainViewModel

class CurrentWeatherFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = (activity as MainActivity).obtainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentWeather.observe(this, Observer {
            if(it == null) return@Observer
            Log.d("VM", "$it")
            loading.visibility = View.GONE
            updateTemperature(it.temperature.toInt().toString(), "M")

            GlideApp.with(this)
                .load("https://www.weatherbit.io/static/img/icons/${it.weatherIcon}.png")
                .into(weather_icon)
        })
    }

    //    private fun updateLocationTitle(location: String, date: String){
//        (activity as AppCompatActivity).topToolBar?.title = location
//        (activity as AppCompatActivity).topToolBar?.subtitle = date
//    }

    private fun updateTemperature(temperature: String, units: String){
        val _units = if (units == "M") "°C" else "°F"
        view_temperature.text = "$temperature $_units"
    }
}