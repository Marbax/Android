package com.marbax.movieapi.movie_list

import com.marbax.movieapi.model.Movie

interface MovieListContract {

    interface Model {

        interface OnFinishedListener {

            fun onSuccess(movies: List<Movie>?)
            fun onFailure(t: Throwable)
        }

        fun getMovieList(onFinishedListener: OnFinishedListener)
    }

    interface View {

        fun showProgress()
        fun hideProgress()
        fun submitMovies(movies: List<Movie>?)
        fun showError(t: Throwable)
    }

    interface Presenter {

        fun getMovies()
    }

}