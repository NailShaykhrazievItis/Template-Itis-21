package com.itis.templateitis.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.templateitis.domain.entity.Weather
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var _weather: MutableLiveData<Result<Weather>> = MutableLiveData()
    val weather: LiveData<Result<Weather>> = _weather

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetWeatherClick(city: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)
                _weather.value = Result.success(weather)
            } catch (ex: Exception) {
                _weather.value = Result.failure(ex)

                _error.value = ex
            }
        }
    }
}
