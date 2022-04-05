package com.itis.templateitis.presentation.weather

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.App
import com.itis.templateitis.R
import com.itis.templateitis.utils.AppViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.HttpException
import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: AppViewModelFactory

    private val viewModel: WeatherViewModel by viewModels {
        factory
    }

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
