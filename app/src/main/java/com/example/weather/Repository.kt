package com.example.weather

import javax.inject.Inject

class Repository @Inject constructor() {
    suspend fun getForecast(cityName: String): Forecast {
        return RetrofitServices.getDataList.getForecast(cityName)
    }
}