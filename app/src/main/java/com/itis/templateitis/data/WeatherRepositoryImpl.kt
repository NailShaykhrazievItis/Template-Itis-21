package com.itis.templateitis.data

import com.itis.templateitis.data.api.Api
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: Api,
    private val weatherMapper: WeatherMapper,
) : WeatherRepository {

    override fun getWeather(
        city: String
    ): Single<Weather> = api.getWeather(city)
        .map {
            weatherMapper.map(it)
        }
}
