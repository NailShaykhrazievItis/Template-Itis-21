package com.itis.templateitis.domain.usecase

import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetWeatherUseCaseTest {

    lateinit var useCase: GetWeatherUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetWeatherUseCase(
            weatherRepository = TestWeatherRepositoryStub(),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    @DisplayName("Then request weather, we awaiting Weather from City")
    fun invokeTest() {
        // arrange
        val expectedValue = "Paris"
        val expectedWeatherId = 1
        val expectedWeatherName = "Name"

        // act
        val result = useCase(expectedValue)

        // assert
        assertEquals(
            expectedWeatherId,
            result.blockingGet().id
        )
        assertEquals(
            expectedWeatherName,
            result.blockingGet().name
        )
    }

    inner class TestWeatherRepositoryStub : WeatherRepository {

        override fun getWeather(city: String): Single<Weather> {
            return Single.just(Weather(1, "Name", 10, 11.1))
        }
    }
}
