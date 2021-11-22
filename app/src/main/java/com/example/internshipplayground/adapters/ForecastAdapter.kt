package com.example.internshipplayground.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipplayground.R
import com.example.internshipplayground.model.Forecast
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private var dataSet: Forecast? = null) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.date)
        val weatherDescTextView: TextView = view.findViewById(R.id.weather_description_text)
        val temperatureTextView: TextView = view.findViewById(R.id.item_text)
        val windTextView: TextView = view.findViewById(R.id.wind_speed_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temperatureText = "°C: ${String.format("%.2f", dataSet?.hourly?.get(position)?.temp)}"
        val timeText = dataSet?.hourly?.get(position)?.dt?.toLong()?.let { Date(it * 1000) }
        val windSpeedText = "${ dataSet?.hourly?.get(position)?.wind_speed?.toString() } M/s"

        holder.dateTextView.text =
            SimpleDateFormat("dd MMMM yyyy - HH:mm", Locale.getDefault()).format(timeText)
        holder.weatherDescTextView.text = dataSet?.hourly?.get(position)?.weather?.get(0)?.description?.capitalize()
        holder.temperatureTextView.text = temperatureText
        holder.windTextView.text = windSpeedText
    }

    override fun getItemCount(): Int {
        return dataSet?.hourly?.size ?: 0
    }

    fun setData(dataSet: Forecast) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}