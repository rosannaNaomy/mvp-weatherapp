package com.example.myweatherapp

import android.app.Application
import com.example.myweatherapp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class WeatherApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingFragmentInjector

    }

}