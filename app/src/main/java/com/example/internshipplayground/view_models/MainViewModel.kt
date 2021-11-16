package com.example.internshipplayground.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshipplayground.model.Forecast
import com.example.internshipplayground.retrofit.Repo
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repo: Repo) : ViewModel() {

    var forecastLiveData: MutableLiveData<Response<Forecast>> = MutableLiveData()

    var latitude: Int = 60
    var longitude: Int = 30

    fun updateData() {
        viewModelScope.launch {
            val response = repo.getWeather(latitude, longitude)
            if (response.isSuccessful) {
                response.body()?.let {
                    for (i in it.hourly) { i.temp = i.temp.minus(273.15) }
                }
            }
            forecastLiveData.value = response
        }
    }

}