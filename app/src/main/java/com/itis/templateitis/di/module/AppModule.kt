package com.itis.templateitis.di.module

import android.app.Application
import android.content.Context
import com.itis.templateitis.data.api.mapper.WeatherMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideWeatherMapper(): WeatherMapper = WeatherMapper()

    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
