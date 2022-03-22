package com.itis.templateitis.di.module

import com.itis.templateitis.data.WeatherRepositoryImpl
import com.itis.templateitis.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun weatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository
}
