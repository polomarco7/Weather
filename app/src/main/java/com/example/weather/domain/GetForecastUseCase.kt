package com.example.weather.domain

import com.example.weather.entity.ForecastModel
import com.example.weather.data.Repository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getForecast(cityName: String): ForecastModel {
        return repository.getForecast(cityName)
    }
}