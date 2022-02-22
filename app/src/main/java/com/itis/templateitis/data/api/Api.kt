package com.itis.templateitis.data.api

import com.itis.templateitis.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("weather?units=metric&lang=FR")
    suspend fun getWeather(@Query("q") city: String): WeatherResponse
}
