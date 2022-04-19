package com.itis.templateitis.di.module

import com.itis.template.di.scope.ActivityScope
import com.itis.templateitis.presentation.weather.WeatherActivity
import com.itis.templateitis.presentation.weather.WeatherModule
import com.itis.templateitis.presentation.weather.mvp.WeatherMvpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [WeatherModule::class])
    fun contributeWeatherActivity(): WeatherActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [WeatherModule::class])
    fun contributeWeatherMvpActivity(): WeatherMvpActivity
}
