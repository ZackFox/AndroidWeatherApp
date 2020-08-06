package ru.zackfox.myweather.util

import android.content.Context
import android.net.ConnectivityManager

interface NetworkChecker {
    fun isNetworkConnected(): Boolean
}

class NetworkHelper(private val context: Context): NetworkChecker {

    override fun isNetworkConnected (): Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}