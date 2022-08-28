package com.example.myweatherapp.di

import com.example.myweatherapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ForecastFragmentModule::class])
abstract class MainActivityConfigModule {
    @ContributesAndroidInjector
    abstract fun contributeFragment(): MainActivity
}