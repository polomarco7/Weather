package com.example.weather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.ListForecastBinding
import com.example.weather.entity.Forecastday

class ListAdapter : ListAdapter<Forecastday, ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            date.text = item.date
            condition.text= item.day.condition.text
            item.let {
                Glide
                    .with(icon.context)
                    .load(it.day.condition.icon)
                    .into(icon)
            }
            temp.text = "temp ${item.day.avgtempc} C"
            wind.text = "wind ${item.day.maxwindkph} kph"
            humidity.text = " humidity ${item.day.avghumidity} %"
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Forecastday>() {
    override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean = oldItem == newItem
}

class ViewHolder(val binding: ListForecastBinding) : RecyclerView.ViewHolder(binding.root)

