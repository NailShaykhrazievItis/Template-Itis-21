package com.itis.templateitis.domain.usecase

import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    suspend operator fun invoke(city: String): Weather {
        return withContext(dispatcher) {
            weatherRepository.getWeather(city)
        }
    }
}
