package com.itis.templateitis.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var _weather: MutableLiveData<Result<Weather>> = MutableLiveData()
    val weather: LiveData<Result<Weather>> = _weather

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    private val disposables = CompositeDisposable()

    fun onGetWeatherClick(city: String) {
        val single1 = Single.just(1)
        val single2 = Single.just(2)
        val single3 = Single.just(2)

        Singles.zip(single1, single2, single3)
            .subscribeBy(onSuccess = { (one, two, three) ->

            }, onError = {

            })

        Single.zip(single1, single2) { it1, it2 ->
            it1 to it2
        }.subscribeBy(onSuccess = { (sdsa, sdaa) ->
            val one = sdsa
        }, onError = {

        }).addTo(disposables)

        disposables += getWeatherUseCase(city)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // showLoading()
            }
            .doAfterTerminate{
                // hideLoading
            }
            .subscribeBy(onSuccess = {
                _weather.value = Result.success(it)
            }, onError = { error ->
                _weather.value = Result.failure(error)
            })

//        val disposable = getWeatherUseCase(city)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    _weather.value = Result.success(it)
//                }, { error->
//                    _weather.value = Result.failure(error)
//                }
//            )

//        viewModelScope.launch {
//            try {
//                val weather = getWeatherUseCase(city)
//                _weather.value = Result.success(weather)
//            } catch (ex: Exception) {
//                _weather.value = Result.failure(ex)
//
//                _error.value = ex
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
