package com.example.weather_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.model.Forecast
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private var dataSet: Forecast? = null) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.date)
        val weatherDescTextView: TextView = view.findViewById(R.id.weather_description_text)
        val temperatureTextView: TextView = view.findViewById(R.id.item_text)
        val windTextView: TextView = view.findViewById(R.id.wind_speed_text)
        val weatherIcon: ImageView = view.findViewById(R.id.weather_icon)
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

        holder.weatherIcon.apply {
            when(dataSet?.hourly?.get(position)?.weather?.get(0)?.icon) {
                "01d" -> this.setImageResource(R.drawable.a01d2x)
                "01n" -> this.setImageResource(R.drawable.a01n2x)
                "02d" -> this.setImageResource(R.drawable.a02d2x)
                "02n" -> this.setImageResource(R.drawable.a02n2x)
                "03d" -> this.setImageResource(R.drawable.a03d2x)
                "03n" -> this.setImageResource(R.drawable.a03n2x)
                "04d" -> this.setImageResource(R.drawable.a04d2x)
                "04n" -> this.setImageResource(R.drawable.a04n2x)
                "09d" -> this.setImageResource(R.drawable.a09d2x)
                "09n" -> this.setImageResource(R.drawable.a09n2x)
                "10d" -> this.setImageResource(R.drawable.a10d2x)
                "10n" -> this.setImageResource(R.drawable.a10n2x)
                "11d" -> this.setImageResource(R.drawable.a11d2x)
                "11n" -> this.setImageResource(R.drawable.a11n2x)
                "13d" -> this.setImageResource(R.drawable.a13d2x)
                "13n" -> this.setImageResource(R.drawable.a13n2x)
                "50d" -> this.setImageResource(R.drawable.a50n2x)
                "50n" -> this.setImageResource(R.drawable.a50n2x)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet?.hourly?.size ?: 0
    }

    fun setData(dataSet: Forecast) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}