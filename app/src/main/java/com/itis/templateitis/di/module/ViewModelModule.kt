package com.itis.templateitis.di.module

import androidx.lifecycle.ViewModelProvider
import com.itis.templateitis.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory
}
