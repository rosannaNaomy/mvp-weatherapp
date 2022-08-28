package com.example.myweatherapp.view.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.*
import com.example.myweatherapp.network.WeatherResponse
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ForecastFragment : Fragment(), ForecastViewBinder {

    private lateinit var forecastFragmentView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var city: String

    @Inject
    lateinit var presenter: ForecastPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        forecastFragmentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_forecast, container, false)
        return forecastFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.show()

        recyclerView = forecastFragmentView.findViewById(R.id.forecast_recyclerview)

        city = arguments?.getString("city") ?: ""

        setUpRV()
        presenter.search(city)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private fun setUpRV() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            weatherAdapter = WeatherAdapter()
            adapter = weatherAdapter
        }
    }

    override fun showForecast(forecast: List<WeatherResponse.Daily>) {
        weatherAdapter.updateList(forecast)
    }

    override fun showError(errorMsg: String) {
        val errorTextView: TextView = forecastFragmentView.findViewById(R.id.forecast_error_title)
        val errorImageView: ImageView = forecastFragmentView.findViewById(R.id.forecast_error_image)
        val errorView: ConstraintLayout = forecastFragmentView.findViewById(R.id.error_view)

        errorView.visibility = View.VISIBLE
        when (errorMsg) {
            "Not Found" -> {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                errorTextView.text = getString(R.string.forecast_noResults_error_msg)
                errorImageView.setImageResource(R.drawable.ic_round_search_off_24)
            }
            "Network Error" -> {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                errorTextView.text = getString(R.string.forecast_noNetworkConnection_error_msg)
                errorImageView.setImageResource(R.drawable.ic_round_wifi_off_24)
            }
        }

    }

    override fun showCityName(cityName: String) {
        val cityTextView: TextView = forecastFragmentView.findViewById(R.id.forecast_city_title)
        val title = "Weather in $cityName"
        cityTextView.text = title
    }

}


