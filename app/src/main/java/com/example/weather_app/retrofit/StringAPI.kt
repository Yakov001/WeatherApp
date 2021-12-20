package com.example.weather_app.retrofit

import com.example.weather_app.model.Forecast
import com.example.weather_app.utils.Consts.ADDITION_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StringAPI {

    @GET(ADDITION_URL)
    suspend fun getWeather(
        @Query("lat") lat : Int = 60,
        @Query("lon") long : Int = 30
    ) : Response<Forecast>
}