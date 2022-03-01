package com.itis.templateitis.domain.repository

import com.itis.templateitis.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(city: String): Weather
}
