package com.itis.templateitis.domain.repository

import com.itis.templateitis.domain.entity.Weather
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getWeather(city: String): Single<Weather>
}
