package com.example.weather_app.utils

import com.example.weather_app.BuildConfig

object Consts {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    const val ADDITION_URL = "onecall?exclude=daily,current,minutely,alerts" +
            "&units=metric&appid="

    const val WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY
}