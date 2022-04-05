package com.itis.templateitis.di

import com.itis.templateitis.App
import com.itis.templateitis.di.module.AppModule
import com.itis.templateitis.di.module.NetModule
import com.itis.templateitis.di.module.RepoModule
import com.itis.templateitis.di.module.ViewModelModule
import com.itis.templateitis.di.module.ActivityBindsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelModule::class,
        ActivityBindsModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
