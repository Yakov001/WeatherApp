package com.example.internshipplayground.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipplayground.R
import com.example.internshipplayground.model.Forecast
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.time.hours

class StringAdapter(private var dataSet: Forecast? = null) :
    RecyclerView.Adapter<StringAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_text)
        val dateText: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val displayedText = "°C: ${String.format("%.2f", dataSet?.hourly?.get(position)?.temp)}"
        val time = dataSet?.hourly?.get(position)?.dt?.toLong()?.let { Date(it * 1000) }
        holder.textView.text = displayedText
        holder.dateText.text =
            SimpleDateFormat("yyyy/MM/dd - HH:mm", Locale.getDefault()).format(time)
    }

    override fun getItemCount(): Int {
        return dataSet?.hourly?.size ?: 0
    }

    fun setData(dataSet: Forecast) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}