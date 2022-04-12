package com.itis.templateitis.domain.usecase

import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    operator fun invoke(
        city: String
    ): Single<Weather> = weatherRepository.getWeather(city)
        .subscribeOn(Schedulers.io())
}
