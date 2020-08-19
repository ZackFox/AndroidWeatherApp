package ru.zackfox.myweather.ui


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.zackfox.myweather.App
import ru.zackfox.myweather.R
import ru.zackfox.myweather.ui.current.CurrentWeatherFragment
import ru.zackfox.myweather.ui.daily.DailyWeatherFragment
import ru.zackfox.myweather.util.ViewModelFactory
import ru.zackfox.myweather.util.providers.LocationProvider


class MainActivity : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 1001

    lateinit var sharedViewModel: MainViewModel

    lateinit var locationClient : FusedLocationProviderClient

    val locationRequest = LocationRequest().apply {
        interval = 4000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val locationCallback =  LocationCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        locationClient = (application as App).locationClient

        // получаю viewmodel
        sharedViewModel = obtainViewModel()

        // устанавливаю фрагмент
        setupFragment(CurrentWeatherFragment())

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

    override fun onStart() {
        super.onStart()
        requestLocation()
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.start()
    }

    override fun onStop() {
        super.onStop()
        removeLocationUpdates()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    requestLocationUpdates()
                } else {
                    Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun requestLocation() {
        val COARSE_LOCATION =  android.Manifest.permission.ACCESS_COARSE_LOCATION
        val FINE_LOCATION =  android.Manifest.permission.ACCESS_FINE_LOCATION

        if (ActivityCompat.checkSelfPermission(this, COARSE_LOCATION) !== PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(COARSE_LOCATION,FINE_LOCATION), PERMISSION_REQUEST_CODE)
        } else {
            requestLocationUpdates()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun removeLocationUpdates() {
        locationClient.removeLocationUpdates(locationCallback)
    }

    fun updateNavbar (location: String){
        topToolBar.title = location
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun setupFragment(fragment: Fragment): Int {
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun obtainViewModel(): MainViewModel {
        val factory = ViewModelFactory(this, (application as App).repository)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }
}