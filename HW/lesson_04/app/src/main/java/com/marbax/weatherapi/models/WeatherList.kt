package com.marbax.weatherapi.models

import com.google.gson.annotations.SerializedName

data class WeatherList (

	@SerializedName("dt") val dt : Int,
	@SerializedName("sunrise") val sunrise : Int,
	@SerializedName("sunset") val sunset : Int,
	@SerializedName("temp") val temp : Temp,
	@SerializedName("feels_like") val feels_like : FeelsLike,
	@SerializedName("pressure") val pressure : Int,
	@SerializedName("humidity") val humidity : Int,
	@SerializedName("weather") val weather : List<Weather>,
	@SerializedName("speed") val speed : Double,
	@SerializedName("deg") val deg : Int,
	@SerializedName("clouds") val clouds : Int,
	@SerializedName("pop") val pop : Double,
	@SerializedName("snow") val snow : Double
)