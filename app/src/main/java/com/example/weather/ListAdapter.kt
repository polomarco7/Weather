package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.ListForecastBinding

class ListAdapter : ListAdapter<ForecastModel, ViewHolder>(DiffUtilCallback()) {
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
            date.text = item.forecast.forecastday.firstOrNull()?.date ?: ""
            condition.text= item.forecast.forecastday.firstOrNull()?.day?.condition?.text ?: ""
            item.let {
                Glide
                    .with(icon.context)
                    .load(it.forecast.forecastday.firstOrNull()?.day?.condition?.icon)
            }
            temp.text = item.forecast.forecastday.firstOrNull()?.day?.avgtempc.toString()
            wind.text = item.forecast.forecastday.firstOrNull()?.day?.maxwindkph.toString()
            humidity.text = item.forecast.forecastday.firstOrNull()?.day?.avghumidity.toString()
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<ForecastModel>() {
    override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean =
        oldItem.forecast.forecastday.firstOrNull()?.date == newItem.forecast.forecastday.firstOrNull()?.date

    override fun areContentsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean = oldItem == newItem
}

class ViewHolder(val binding: ListForecastBinding) : RecyclerView.ViewHolder(binding.root)

