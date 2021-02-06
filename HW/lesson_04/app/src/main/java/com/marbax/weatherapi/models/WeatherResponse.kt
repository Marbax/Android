package com.marbax.weatherapi.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse (

	@SerializedName("city") val city : City,
	@SerializedName("cod") val cod : Int,
	@SerializedName("message") val message : Double,
	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : List<WeatherList>
)