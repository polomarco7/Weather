package com.example.weather

import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getForecast(cityName: String): ForecastModel {
        return repository.getForecast(cityName)
    }
}