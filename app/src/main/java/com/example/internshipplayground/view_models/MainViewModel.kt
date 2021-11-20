package com.example.internshipplayground.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshipplayground.model.Forecast
import com.example.internshipplayground.retrofit.Repo
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class MainViewModel(private val repo: Repo) : ViewModel() {

    var forecastLiveData: MutableLiveData<Response<Forecast>> = MutableLiveData()

    var latitude: Int = 60
    var longitude: Int = 30

    fun updateData() {
        viewModelScope.launch {
            val response = try {
                repo.getWeather(latitude, longitude)
            } catch (e: IOException) {
                Log.e("request", "No Internet")
                return@launch
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    for (i in it.hourly) {
                        i.temp = i.temp.minus(273.15)
                    }
                }
            }
            forecastLiveData.value = response
        }
    }

}