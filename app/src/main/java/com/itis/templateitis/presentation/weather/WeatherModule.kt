package com.itis.templateitis.presentation.weather

import androidx.lifecycle.ViewModel
import com.itis.templateitis.di.ViewModelKey
import com.itis.templateitis.presentation.weather.mvp.WeatherPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
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
