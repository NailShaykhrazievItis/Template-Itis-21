package com.itis.templateitis.presentation.weather

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.R
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()

        findViewById<Button>(R.id.btn_get_weather).setOnClickListener {
            viewModel.onGetWeatherClick("Paris")
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
}
