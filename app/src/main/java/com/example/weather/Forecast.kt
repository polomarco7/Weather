package com.example.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "now") val now: Long,
    @Json(name = "now_dt") val now_dt: String,
    @Json(name = "fact") val fact: Fact
    )
@JsonClass(generateAdapter = true)
data class Fact(
    val temp: Int
)
