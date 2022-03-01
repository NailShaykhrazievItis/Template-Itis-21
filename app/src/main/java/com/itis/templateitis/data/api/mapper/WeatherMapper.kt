package com.itis.templateitis.data.api.mapper

import com.itis.templateitis.data.api.response.WeatherResponse
import com.itis.templateitis.domain.entity.Weather

class WeatherMapper {

    fun map(response: WeatherResponse): Weather = Weather(
        id = response.id,
        name = response.name,
        cod = response.cod,
        temp = response.main.temp
    )
}
