package com.example.internshipplayground.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipplayground.R
import com.example.internshipplayground.adapters.ForecastAdapter
import com.example.internshipplayground.retrofit.Repo
import com.example.internshipplayground.utils.Resource.Error
import com.example.internshipplayground.utils.Resource.Success
import com.example.internshipplayground.view_models.MainViewModel
import com.example.internshipplayground.view_models.MainViewModelFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ForecastAdapter() }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.bottom_app_bar))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val buttonGetData = findViewById<Button>(R.id.button_send_request)
        val editTextSetLat = findViewById<EditText>(R.id.select_latitude)
        val editTextSetLong = findViewById<EditText>(R.id.select_longitude)

        viewModel = ViewModelProvider(this, MainViewModelFactory(Repo()))[MainViewModel::class.java]

        recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }

        buttonGetData.setOnClickListener {
            viewModel.updateData()
        }

        viewModel.forecastLiveData.observe(this, Observer { resource ->
            when (resource) {
                is Success -> adapter.setData(resource.data!!)
                is Error -> {
                    Snackbar.make(
                        findViewById(R.id.coordinator),
                        resource.message!!,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })

        editTextSetLat.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString() != "-") {
                viewModel.latitude = text.toString().toInt()
                    .coerceAtMost(90)
                    .coerceAtLeast(-90)
            }

        }

        editTextSetLong.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.toString() != "-") {
                viewModel.longitude = text.toString().toInt()
                    .coerceAtMost(180)
                    .coerceAtLeast(-180)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val modalBottomSheet = ModalBottomSheet()
                modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
            }
        }
        return true
    }
}