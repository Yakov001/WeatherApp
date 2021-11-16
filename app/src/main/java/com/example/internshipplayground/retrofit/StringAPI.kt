package com.example.internshipplayground.retrofit

import com.example.internshipplayground.model.Forecast
import com.example.internshipplayground.utils.Consts.ADDITION_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StringAPI {

    @GET(ADDITION_URL)
    suspend fun getWeather(
        @Query("lat") lat : Double = 60.0,
        @Query("lon") long : Double = 30.0
    ) : Response<Forecast>
}