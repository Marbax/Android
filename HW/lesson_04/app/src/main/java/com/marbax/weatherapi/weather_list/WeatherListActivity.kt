package com.marbax.weatherapi.weather_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbax.weatherapi.R
import com.marbax.weatherapi.adapters.WeatherAdapter
import com.marbax.weatherapi.models.WeatherList
import kotlinx.android.synthetic.main.activity_weather_list.*

// Создать приложение, получающее информацию о текущей погоде и прогноз на 16 дней
//      с сервиса https://openweathermap.org.

// https://api.openweathermap.org/data/2.5/forecast/daily?q=kiev&appid=4a817815ddffc203e7ef99476becf971
// https://openweathermap.org/img/wn/10d@2x.png

class WeatherListActivity : AppCompatActivity(), WeatherListContract.View {

    private var adapter: WeatherAdapter? = null
    private val presenter = WeatherListPresenter(this)
    private val weatherList = mutableListOf<WeatherList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

        presenter.getWeather()
        rvWeather.layoutManager = LinearLayoutManager(this)
        adapter = WeatherAdapter(weatherList)
        rvWeather.adapter = adapter
    }

    override fun showProgress() {
        pbWeatherList.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbWeatherList.visibility = View.GONE
    }

    override fun submitWeather(weather: List<WeatherList>?) {
        weather?.apply {
            weatherList.addAll(this)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, "Error! ${t.message}", Toast.LENGTH_LONG).show()
        Log.e(WeatherListActivity::class.java.simpleName, t.message.toString())
    }
}