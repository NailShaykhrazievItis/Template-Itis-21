package com.itis.templateitis.presentation.weather.mvp

import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : MvpPresenter<WeatherView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    override fun attachView(view: WeatherView?) {
        super.attachView(view)

    }

    override fun detachView(view: WeatherView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onGetWeatherClick(city: String) {
        disposables += getWeatherUseCase(city)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showCurrentTemp(it.temp.toString())
                viewState.openDetailsScreen()
            }, onError = { error ->
                viewState.showError(error)
            })
    }
}
