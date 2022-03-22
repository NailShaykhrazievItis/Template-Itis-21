package com.itis.templateitis.di.module

import com.itis.templateitis.data.WeatherRepositoryImpl
import com.itis.templateitis.data.api.Api
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.domain.repository.WeatherRepository
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {

    @Provides
    fun provideWeatherMapper(): WeatherMapper = WeatherMapper()

    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
