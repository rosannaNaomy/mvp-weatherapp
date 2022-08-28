package com.example.myweatherapp.network

import com.example.myweatherapp.network.WeatherResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("onecall?")
    fun getWeatherFromSearch(@QueryMap searchQuery: Map<String,String>): Observable<WeatherResponse>

}