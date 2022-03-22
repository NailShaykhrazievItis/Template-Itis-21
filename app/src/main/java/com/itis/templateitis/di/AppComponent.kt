package com.itis.templateitis.di

import com.itis.templateitis.di.module.AppModule
import com.itis.templateitis.di.module.NetModule
import com.itis.templateitis.di.module.RepoModule
import com.itis.templateitis.di.module.ViewModelModule
import com.itis.templateitis.presentation.MainActivity
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
