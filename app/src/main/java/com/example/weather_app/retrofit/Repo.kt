package com.example.weather_app.retrofit

import com.example.weather_app.model.Forecast
import retrofit2.Response

class Repo {

    suspend fun getWeather(lat: Int, long: Int): Response<Forecast> {
        return RetrofitInstance.api.getWeather(lat, long)
    }

}