package com.example.internshipplayground.retrofit

import com.example.internshipplayground.model.Forecast
import com.example.internshipplayground.utils.Consts.ADDITION_URL
import retrofit2.Response
import retrofit2.http.GET

interface StringAPI {

    @GET(ADDITION_URL)
    suspend fun getWeather() : Response<Forecast>
}