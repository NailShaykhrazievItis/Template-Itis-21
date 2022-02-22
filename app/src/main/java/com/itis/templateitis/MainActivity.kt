package com.itis.templateitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.data.WeatherRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val repository by lazy {
        WeatherRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById<Button>(R.id.btn_get_weather).setOnClickListener {
            getWeather()
        }
    }

    private fun getWeather() {
        lifecycleScope.launch {
            try {
                val response = repository.getWeather("Paris")
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Temp: ${response.main.temp} C",
                    Snackbar.LENGTH_LONG
                ).show()
            } catch (ex: Exception) {
                Log.e("asd", ex.message.toString())
            }
        }
    }
}
