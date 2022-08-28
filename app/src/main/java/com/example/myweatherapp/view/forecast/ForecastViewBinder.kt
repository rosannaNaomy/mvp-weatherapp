package com.example.myweatherapp.view.forecast

import com.example.myweatherapp.network.WeatherResponse

interface ForecastViewBinder {

    fun showForecast(forecast: List<WeatherResponse.Daily>)
    fun showError(errorMsg: String)
    fun showCityName(cityName: String)

}