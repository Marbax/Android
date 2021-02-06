package com.marbax.weatherapi.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://api.openweathermap.org/data/2.5/forecast/daily?q=kiev&appid=4a817815ddffc203e7ef99476becf971
// https://openweathermap.org/img/wn/10d@2x.png
object ApiClient {
    private const val URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = "4a817815ddffc203e7ef99476becf971"
    const val CITY = "kiev"
    const val UNITS = "metric"
    const val DAYS = "16"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun apiImgUrl(imgName: String): String {
        return "https://openweathermap.org/img/wn/${imgName}@2x.png"
    }
}