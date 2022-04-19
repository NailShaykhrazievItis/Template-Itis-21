package com.itis.templateitis.presentation.weather.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.*

@AddToEndSingle
interface WeatherView : MvpView {

    fun showCurrentTemp(temp: String)

    @Skip
    fun showError(throwable: Throwable)

    fun showLoading()

    fun hideLoading()

    @OneExecution
    fun openDetailsScreen()
}
