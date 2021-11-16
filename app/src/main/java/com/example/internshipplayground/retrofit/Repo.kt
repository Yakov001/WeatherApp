package com.example.internshipplayground.retrofit

import com.example.internshipplayground.model.Forecast
import retrofit2.Response

class Repo {

    suspend fun getWeather(lat: Int, long: Int): Response<Forecast> {
        return RetrofitInstance.api.getWeather(lat, long)
    }

}