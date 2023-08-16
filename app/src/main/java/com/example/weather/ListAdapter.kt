package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ListCityBinding

class ListAdapter : ListAdapter<Forecast, ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            name.text = (item?.fact ?: "").toString()
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean =
        oldItem.now == newItem.now

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean = oldItem == newItem
}

class ViewHolder(val binding: ListCityBinding) : RecyclerView.ViewHolder(binding.root)

