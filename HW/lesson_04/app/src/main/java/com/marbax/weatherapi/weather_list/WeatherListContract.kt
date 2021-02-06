package com.marbax.weatherapi.weather_list

import com.marbax.weatherapi.models.Weather
import com.marbax.weatherapi.models.WeatherList


interface WeatherListContract {

    interface Model {

        interface OnFinishedListener {

            fun onSuccess(weather: List<WeatherList>?)
            fun onFailure(t: Throwable)
        }

        fun getWeatherList(onFinishedListener: OnFinishedListener)
    }

    interface View {

        fun showProgress()
        fun hideProgress()
        fun submitWeather(weather: List<WeatherList>?)
        fun showError(t: Throwable)
    }

    interface Presenter {

        fun getWeather()
    }

}