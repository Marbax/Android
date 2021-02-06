package com.marbax.weatherapi.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.marbax.weatherapi.R
import com.marbax.weatherapi.models.WeatherList
import com.marbax.weatherapi.network.ApiClient
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(private val weatherList: List<WeatherList>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivWeatherIcon: ImageView = itemView.findViewById(R.id.ivWeatherIcon)
        private val tvWeatherDescription: TextView =
            itemView.findViewById(R.id.tvWeatherDescription)
        private val tvMinTemp: TextView =
            itemView.findViewById(R.id.tvMinTemp)
        private val tvMaxTemp: TextView =
            itemView.findViewById(R.id.tvMaxTemp)
        private val tvWeatherDate: TextView =
            itemView.findViewById(R.id.tvWeatherDate)
        private val pbIconLoading: ProgressBar =
            itemView.findViewById(R.id.pbIconLoading)

        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindWeather(weatherList: WeatherList) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = Date(weatherList.dt.toLong() * 1000)
            tvWeatherDate.text = sdf.format(date)
            tvWeatherDescription.text = weatherList.weather[0].description.capitalize()
            tvMinTemp.text = "Min: ${weatherList.temp.min} C"
            tvMaxTemp.text = "Max: ${weatherList.temp.max} C"

            Glide.with(itemView.context)
                .load(ApiClient.apiImgUrl(weatherList.weather[0].icon))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbIconLoading.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbIconLoading.visibility = View.GONE
                        return false
                    }
                })
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_weather_ph)
                        .error(R.drawable.ic_weather_ph)
                )
                .into(ivWeatherIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_card, parent, false)
        )
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bindWeather(weather)
    }

}