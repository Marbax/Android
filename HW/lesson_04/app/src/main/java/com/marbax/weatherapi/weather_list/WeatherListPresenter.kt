package com.marbax.weatherapi.weather_list

import com.marbax.weatherapi.models.WeatherList


class WeatherListPresenter(private val weatherListView: WeatherListContract.View) :
    WeatherListContract.Presenter, WeatherListContract.Model.OnFinishedListener {

    private val weatherListModel = WeatherListModel()

    override fun getWeather() {
        weatherListView.showProgress()
        weatherListModel.getWeatherList(this)
    }

    override fun onSuccess(weatherList: List<WeatherList>?) {
        weatherListView.submitWeather(weatherList)
        weatherListView.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        weatherListView.showError(t)
        weatherListView.hideProgress()
    }
}