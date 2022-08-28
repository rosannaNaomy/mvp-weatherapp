package com.example.myweatherapp.view.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.network.WeatherResponse
import com.squareup.picasso.Picasso

class WeatherAdapter() :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var listData = mutableListOf<WeatherResponse.Daily>()

    fun updateList(weatherList: List<WeatherResponse.Daily>) {
        this.listData.clear()
        this.listData.addAll(weatherList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)

        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleWeatherText: TextView = view.findViewById(R.id.weather_item_title_text)
        val highWeatherText: TextView = view.findViewById(R.id.weather_high_temp)
        val lowWeatherText: TextView = view.findViewById(R.id.weather_low_temp)
        val iconWeather: ImageView = view.findViewById(R.id.weather_item_icon)
        val dateTextView: TextView = view.findViewById(R.id.weather_item_date)

        fun bind(daily: WeatherResponse.Daily) {
            val hiTemp = "High: ${daily.temp.max.toInt()}\u2109"
            val loTemp = "Low: ${daily.temp.min.toInt()}\u2109"

            titleWeatherText.text = daily.weatherType[0].description
            highWeatherText.text = hiTemp
            lowWeatherText.text = loTemp
            dateTextView.text = daily.getDate()

            Picasso.get().load(daily.weatherType[0].getIconUrl()).into(iconWeather)

        }
    }


}