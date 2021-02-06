package com.marbax.movieapi.movie_list

import com.marbax.movieapi.model.MovieResponse
import com.marbax.movieapi.network.ApiClient
import com.marbax.movieapi.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListModel : MovieListContract.Model {
    private val apiService = ApiClient.buildService(ApiService::class.java)
    private val call = apiService.getmovies()

    override fun getMovieList(onFinishedListener: MovieListContract.Model.OnFinishedListener) {
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()?.results
                onFinishedListener.onSuccess(movies)
            }

        })
    }
}