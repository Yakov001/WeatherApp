package com.example.internshipplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipplayground.R
import com.example.internshipplayground.adapters.StringAdapter
import com.example.internshipplayground.model.Forecast
import com.example.internshipplayground.model.Hourly
import com.example.internshipplayground.model.Weather
import com.example.internshipplayground.retrofit.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val repo = Repo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val buttonGetData = findViewById<Button>(R.id.button_send_request)

        buttonGetData.setOnClickListener {
            getDataFromAPI(recyclerView.adapter as StringAdapter)
        }

        recyclerView.also {
            it.adapter = StringAdapter()
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun getDataFromAPI(adapter: StringAdapter) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = repo.getWeather()
            if (response.isSuccessful) {
                response.body()?.let {
                    for (i in it.hourly) {
                        i.temp = i.temp.minus(273.15)
                    }
                    adapter.setData(it)
                }
            } else {
                Toast.makeText(
                    this@MainActivity.baseContext,
                    response.code(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}