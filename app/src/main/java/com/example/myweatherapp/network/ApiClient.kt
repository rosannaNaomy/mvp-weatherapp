package com.example.myweatherapp.network

import io.reactivex.rxjava3.core.Observable

class ApiClient(private val weatherService: WeatherService) {

    companion object {
        private const val APIKEY = "8fc57ea4b5d7e0dc15846a998691b6a9"
        private const val EXCLUDE = "minutely,hourly"
        private const val UNITS = "imperial"
    }

    fun getWeatherFromSearch(lat: Double, lon: Double): Observable<WeatherResponse> {
        val searchQuery = mapOf(
            "lat" to lat.toString(),
            "lon" to lon.toString(),
            "exclude" to EXCLUDE,
            "units" to UNITS,
            "appid" to APIKEY
        ) //Query map

       return weatherService.getWeatherFromSearch(searchQuery)
    }



}