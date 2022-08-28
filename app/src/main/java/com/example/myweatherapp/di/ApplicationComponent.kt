package com.example.myweatherapp.di

import android.app.Application
import android.content.Context
import com.example.myweatherapp.WeatherApplication
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule

@Component(
    modules = [
        LocationModule::class,
        MainActivityConfigModule::class,
        AndroidInjectionModule::class,
        Binding::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: WeatherApplication)

}

@Module
interface Binding {
    @Binds
    fun context(app: Application): Context
}