package com.itis.templateitis

import android.app.Application
import com.itis.templateitis.di.AppComponent
import com.itis.templateitis.di.module.AppModule
import com.itis.templateitis.di.DaggerAppComponent
import com.itis.templateitis.di.module.NetModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .netModule(NetModule())
            .build()
    }
}
