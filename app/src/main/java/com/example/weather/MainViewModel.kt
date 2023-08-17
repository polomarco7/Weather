package com.example.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
    ) : ViewModel() {

    private var _forecast = MutableStateFlow<List<ForecastModel>>(emptyList())
    val forecast: StateFlow<List<ForecastModel>> = _forecast.asStateFlow()
    private var forecastList: List<ForecastModel> = emptyList()

    fun getForecast(city: String) {
        viewModelScope.launch {
            val forecast = getForecastUseCase.getForecast(city).forecast

            forecastList = listOf(
                ForecastModel(forecast = forecast))

            _forecast.value = forecastList
        }
    }
}