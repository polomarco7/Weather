package com.example.weather.data

import com.example.weather.entity.ForecastModel
import javax.inject.Inject

class Repository @Inject constructor() {
    suspend fun getForecast(cityName: String): ForecastModel {
        return RetrofitServices.getDataList.getForecast(cityName)
    }
}