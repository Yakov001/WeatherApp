package com.example.internshipplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipplayground.R
import com.example.internshipplayground.adapters.ForecastAdapter
import com.example.internshipplayground.retrofit.Repo
import com.example.internshipplayground.view_models.MainViewModel
import com.example.internshipplayground.view_models.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ForecastAdapter() }
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val buttonGetData = findViewById<Button>(R.id.button_send_request)
        viewModel = ViewModelProvider(this, MainViewModelFactory(Repo()))[MainViewModel::class.java]

        recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }

        buttonGetData.setOnClickListener {
            viewModel.updateData()
        }

        viewModel.forecastLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}