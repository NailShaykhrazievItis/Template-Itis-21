package com.itis.templateitis.presentation.weather.mvp

import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class WeatherPresenterTest {

    @MockK
    lateinit var weatherUseCase: GetWeatherUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `WeatherView$$State`

    lateinit var presenter: WeatherPresenter

    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = spyk(
            WeatherPresenter(getWeatherUseCase = weatherUseCase)
        )
        presenter.setViewState(viewState)
    }

    @Test
    fun `When searching city, we will expect success and show temp info`() {
        // arrange
        val expectedCity = "Kazan"
        val expectedTemp = 22.2
        val mockWeather = mockk<Weather>() {
            every { temp } returns expectedTemp
        }
        every { weatherUseCase.invoke(expectedCity) } returns Single.just(mockWeather)

        // act
        presenter.onGetWeatherClick(expectedCity)

        // assert
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify {
            viewState.showCurrentTemp(expectedTemp.toString())
            viewState.openDetailsScreen()
        }
    }

    @Test
    fun `When searching city, we will expect error and show message`() {
        // arrange
        val expectedCity = "Kazan"
        val mockError = mockk<Throwable>()
        every { weatherUseCase.invoke(expectedCity) } returns Single.error(mockError)

        // act
        presenter.onGetWeatherClick(expectedCity)

        // assert
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }
        verify(inverse = true) {
            viewState.showCurrentTemp(any())
            viewState.openDetailsScreen()
        }
        verify {
            viewState.showError(mockError)
        }
    }
}
