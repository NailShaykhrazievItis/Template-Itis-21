package com.itis.templateitis.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.App
import com.itis.templateitis.R
import com.itis.templateitis.utils.AppViewModelFactory
import retrofit2.HttpException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AppViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
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
