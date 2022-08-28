package com.example.myweatherapp.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherResponse(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double
) {
    data class Daily(
        var temp: Temp,
        val dt: Int,
        @SerializedName("weather")
        val weatherType: List<WeatherType>
    ){
        fun getDate(): String {
            val date = Date(dt * 1000L)
            val c: Calendar = Calendar.getInstance()
            c.time = date
            val dateLocal = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)
            val day = c.get(Calendar.DATE)
            val month = c.get(Calendar.MONTH) + 1

            return "$dateLocal $month|$day"
        }
    }

    data class WeatherType(
        val main: String,
        val description: String,
        val icon: String,
        ) {
        fun getIconUrl(): String = "https://openweathermap.org/img/wn/${icon}@4x.png"
    }

    data class Temp(
        var day: Double,
        var min: Double,
        var max: Double,
        var night: Double
    )

}

