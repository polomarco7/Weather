package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.GetForecastUseCase
import com.example.weather.entity.Forecastday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
    ) : ViewModel() {

    private var _forecast = MutableStateFlow<List<Forecastday>>(emptyList())
    val forecast: StateFlow<List<Forecastday>> = _forecast.asStateFlow()

    fun getForecast(city: String) {
        viewModelScope.launch {
            getForecastUseCase.getForecast(city)

        }
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getForecastUseCase.getForecast(city)
            }.fold(
                onSuccess = { _forecast.value = it.forecast.forecastday },
                onFailure = { Log.d("ForecastViewModel", it.message ?: "") }
            )
        }
    }
}