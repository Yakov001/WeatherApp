package com.example.internshipplayground.model

data class Forecast(
    val lat: Int,
    val lon: Int,
    val timezone: String,
    val timezone_offset: Int,
    val hourly: List<Hourly>
)