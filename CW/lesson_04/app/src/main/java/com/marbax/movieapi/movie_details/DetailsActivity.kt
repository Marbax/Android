package com.marbax.movieapi.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marbax.movieapi.Const.KEY_MOVIE_ID
import com.marbax.movieapi.R
import com.marbax.movieapi.model.Movie
import com.marbax.movieapi.movie_list.MovieListActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsContract.View {

    private val presenter = MoviePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id = intent.getIntExtra(KEY_MOVIE_ID, -1)
        presenter.getMovie(id)
    }

    override fun showProgress() {
        pbMovieDetailsLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbMovieDetailsLoading.visibility = View.GONE
    }

    override fun showMovieDetails(movie: Movie) {
        tvMovieВetails.text = movie.toString()
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, "Error! ${t.message}", Toast.LENGTH_SHORT).show()
        Log.e(MovieListActivity::class.java.simpleName, t.message.toString())
    }
}