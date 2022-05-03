package com.itis.templateitis.domain.usecase

import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.repository.WeatherRepository
import com.itis.templateitis.utils.MainCoroutineRule
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetWeatherUseCaseTestMockk {

    @MockK
    lateinit var repository: WeatherRepository

    lateinit var useCase: GetWeatherUseCase

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    @BeforeEach
    fun setUp() {
        useCase = GetWeatherUseCase(
            weatherRepository = repository,
            dispatcher = coroutineRule.testDispatcher
        )
    }

    @Test
    @DisplayName("Then request weather, we awaiting Weather from City")
    fun invokeTest() {
        // arrange
        val expectedValue = "Paris"
        val expectedWeatherId = 1
        val expectedWeatherName = "Name"
        val mockWeather = mockk<Weather>() {
            every { id } returns expectedWeatherId
            every { name } returns expectedWeatherName
        }
        every {
            repository.getWeather(expectedValue)
        } returns Single.just(mockWeather)

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

    @Test
    @DisplayName("Then request weather, we awaiting error")
    fun invokeTestException() {
        // arrange
        val expectedValue = "Paris"
        val mockError = mockk<Throwable>()
        every {
            repository.getWeather(expectedValue)
        } returns Single.error(mockError)

        // act
        val result = useCase(expectedValue)

        // assert
        assertEquals(
            mockError,
            result.blockingGet()
        )
    }
}

