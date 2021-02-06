package com.marbax.weatherapi.network

import com.marbax.weatherapi.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast/daily")
    fun getWeather(
        @Query("q") city: String = ApiClient.CITY,
        @Query("units") units: String = ApiClient.UNITS,
        @Query("cnt") days: String = ApiClient.DAYS,
        @Query("appid") apiKey: String = ApiClient.API_KEY
    ):
            Call<WeatherResponse>

}