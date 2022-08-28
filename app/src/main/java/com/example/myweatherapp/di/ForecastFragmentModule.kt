package com.example.myweatherapp.di

import com.example.myweatherapp.view.forecast.ForecastFragment
import com.example.myweatherapp.view.forecast.ForecastViewBinder
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ForecastFragmentModule {
    @ContributesAndroidInjector(modules = [ForecastFragmentModule.Binding::class])
    abstract fun contributeFragment(): ForecastFragment

    @Module
    interface Binding {
        @Binds
        fun bindViewBinder(fragment: ForecastFragment): ForecastViewBinder
    }
}