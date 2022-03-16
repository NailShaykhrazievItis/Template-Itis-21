package com.itis.templateitis.di

import com.itis.templateitis.BuildConfig
import com.itis.templateitis.data.WeatherRepositoryImpl
import com.itis.templateitis.data.api.Api
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.domain.repository.WeatherRepository
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "56fc6c6cb76c0864b4cd055080568268"
private const val QUERY_API_KEY = "appid"

object DIContainer {

    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }

    private val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                    )
                }
            }
            .build()
    }

    private val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        api = api,
        weatherMapper = WeatherMapper()
    )

    val getWeatherUseCase: GetWeatherUseCase = GetWeatherUseCase(
        weatherRepository = weatherRepository,
        dispatcher = Dispatchers.Default
    )
}
