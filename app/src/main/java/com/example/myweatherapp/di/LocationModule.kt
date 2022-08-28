package com.example.myweatherapp.di

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import java.util.*

@Module
object LocationModule {

    @Provides
    fun provideGeocoder(context: Context): Geocoder {
        return Geocoder(context, Locale.US)
    }
}