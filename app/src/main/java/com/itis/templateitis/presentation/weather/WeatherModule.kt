package com.itis.templateitis.presentation.weather

import androidx.lifecycle.ViewModel
import com.itis.templateitis.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WeatherModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindMainViewModel(
        viewModel: WeatherViewModel
    ): ViewModel
}
