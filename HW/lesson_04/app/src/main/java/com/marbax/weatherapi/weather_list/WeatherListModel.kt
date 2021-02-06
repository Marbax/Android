package com.marbax.weatherapi.weather_list

import com.marbax.weatherapi.models.WeatherResponse
import com.marbax.weatherapi.network.ApiClient
import com.marbax.weatherapi.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherListModel : WeatherListContract.Model {
    private val apiService = ApiClient.buildService(ApiService::class.java)
    private val call = apiService.getWeather()

    override fun getWeatherList(onFinishedListener: WeatherListContract.Model.OnFinishedListener) {
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()?.list
                onFinishedListener.onSuccess(weather)
            }

        })
    }
}