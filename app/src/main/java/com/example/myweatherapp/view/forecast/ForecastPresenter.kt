package com.example.myweatherapp.view.forecast

import android.location.Geocoder
import android.util.Log
import com.example.myweatherapp.network.ApiClient
import com.example.myweatherapp.network.WeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject


class ForecastPresenter @Inject constructor(
    private val viewBinder: ForecastViewBinder,
    private val gc: Geocoder,
    private val apiClient: ApiClient
) {

    private lateinit var cityName: String

    fun search(city: String) {
        try {
            val addresses = gc.getFromLocationName(city, 1)
            if (addresses.size > 0) {
                val lat = addresses[0].latitude
                val lon = addresses[0].longitude
                cityName = addresses[0].subLocality ?: addresses[0].featureName
                fetchWeather(lat, lon)
            } else {
                viewBinder.showError("Not Found")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            viewBinder.showError("Network not available")
        }
    }

    private fun fetchWeather(lat: Double, lon: Double) {
        val weatherObservable: Observable<WeatherResponse> =
            apiClient.getWeatherFromSearch(lat, lon)

        weatherObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(weatherResponse: WeatherResponse) {
        viewBinder.showForecast(weatherResponse.daily)
        viewBinder.showCityName(cityName)
    }

    private fun handleError(t: Throwable) {
        viewBinder.showError("Network Error")
        Log.d("HandleError", t.toString())
    }


}