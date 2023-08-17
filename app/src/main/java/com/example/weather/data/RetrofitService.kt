package com.example.weather.data

import com.example.weather.entity.ForecastModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com"

object RetrofitServices {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val getDataList: GetDataList = retrofit.create(GetDataList::class.java)
}

interface GetDataList {
    @Headers("key: $api_key")
    @GET("/v1/forecast.json")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("days") days: Int = 5,
    ): ForecastModel

    private companion object {
        private const val api_key = "9260986f2592439f87a211518231608"
    }
}