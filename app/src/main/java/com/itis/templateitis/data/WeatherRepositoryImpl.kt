package com.itis.templateitis.data

import com.itis.templateitis.data.api.Api
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: Api,
    private val weatherMapper: WeatherMapper,
) : WeatherRepository {

    override suspend fun getWeather(
        city: String
    ): Weather = weatherMapper.map(api.getWeather(city))
}
