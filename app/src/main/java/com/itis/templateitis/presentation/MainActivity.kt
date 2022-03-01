package com.itis.templateitis.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.R
import com.itis.templateitis.data.WeatherRepositoryImpl
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.di.DIContainer
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var getWeatherUseCase: GetWeatherUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObjects()

        findViewById<Button>(R.id.btn_get_weather).setOnClickListener {
            getWeather()
        }
    }

    private fun getWeather() {
        lifecycleScope.launch {
            try {
                val weather = getWeatherUseCase("Paris")
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Temp: ${weather.temp} C",
                    Snackbar.LENGTH_LONG
                ).show()
            } catch (ex: Exception) {
                Log.e("asd", ex.message.toString())
            }
        }
    }

    private fun initObjects() {
        getWeatherUseCase = GetWeatherUseCase(
            weatherRepository = WeatherRepositoryImpl(
                api = DIContainer.api,
                weatherMapper = WeatherMapper()
            ),
            dispatcher = Dispatchers.Default
        )
    }
}
