package com.example.myweatherapp.di

import com.example.myweatherapp.network.ApiClient
import com.example.myweatherapp.network.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    private const val BASEURL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASEURL)
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    fun provideApiClient(weatherService: WeatherService): ApiClient {
        return ApiClient(weatherService)

    }
}