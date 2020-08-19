package ru.zackfox.myweather.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.zackfox.myweather.data.WeatherRepository
import ru.zackfox.myweather.ui.MainViewModel

class ViewModelFactory (
    owner: SavedStateRegistryOwner,
    private val repository: WeatherRepository,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
}