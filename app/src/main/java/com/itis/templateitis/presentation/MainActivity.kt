package com.itis.templateitis.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.R
import com.itis.templateitis.data.WeatherRepositoryImpl
import com.itis.templateitis.data.api.mapper.WeatherMapper
import com.itis.templateitis.di.DIContainer
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import com.itis.templateitis.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObjects()
        initObservers()

        findViewById<Button>(R.id.btn_get_weather).setOnClickListener {
            viewModel.onGetWeatherClick()
        }
    }

    private fun initObservers() {
        viewModel.weather.observe(this) {
            it.fold(onSuccess = {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Temp: ${it.temp} C",
                    Snackbar.LENGTH_LONG
                ).show()
            }, onFailure = {
                Log.e("asd", it.message.toString())
            })
        }

        viewModel.error.observe(this) {
            when (it) {
                is HttpException -> {

                }
                is NullPointerException -> {

                }
                else -> {

                }
            }
        }
    }

    private fun initObjects() {
        val factory = ViewModelFactory(DIContainer)
        viewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]
    }
}
