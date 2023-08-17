package com.example.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastModel(
    @Json(name = "forecast") val forecast: Forecast,
    )
@JsonClass(generateAdapter = true)
data class Forecast(
    val forecastday: List<Forecastday>
)
@JsonClass(generateAdapter = true)
data class Forecastday(
    val date: String,
    val day: Day
)

@JsonClass(generateAdapter = true)
data class Day(
    @Json(name = "avgtemp_c")val avgtempc: Double,
    @Json(name = "maxwind_kph")val maxwindkph: Double,
    val avghumidity:Double,
    val condition:Condition
)

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String,
    val icon: String
)