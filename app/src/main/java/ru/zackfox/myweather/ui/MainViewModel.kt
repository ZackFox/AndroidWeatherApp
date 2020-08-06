package ru.zackfox.myweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zackfox.myweather.data.WeatherRepository
import ru.zackfox.myweather.data.local.entity.CurrentEntity

class MainViewModel(private val repository: WeatherRepository): ViewModel() {
    val currentWeather = MutableLiveData<CurrentEntity>()
    val loading = MutableLiveData<Boolean>(true)

    fun getCurrentWeather(lat: Double, lon: Double, lang: String, units: String) {
        // get data
        this.viewModelScope.launch {
            val current = repository.getCurrentWeather(lat,lon,lang,units)
            loading.postValue(false);
            currentWeather.postValue(current);
        }
    }
}
