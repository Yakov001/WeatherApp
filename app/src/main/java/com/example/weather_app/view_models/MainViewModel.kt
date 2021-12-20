package com.example.weather_app.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.model.Forecast
import com.example.weather_app.retrofit.Repo
import com.example.weather_app.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val repo: Repo) : ViewModel() {

    var forecastLiveData: MutableLiveData<Resource<Forecast?>> = MutableLiveData()

    var latitude: Int = 60
    var longitude: Int = 30

    fun updateData() {
        viewModelScope.launch {
            val response = try {
                repo.getWeather(latitude, longitude)
            } catch (e: IOException) {
                Log.e("request", "No Internet")
                forecastLiveData.value = Resource.Error(null, "No Internet")
                return@launch
            }
            forecastLiveData.value = Resource.Success(response.body())
        }
    }

}