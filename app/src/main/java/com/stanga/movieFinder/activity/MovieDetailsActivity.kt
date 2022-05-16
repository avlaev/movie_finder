package com.stanga.movieFinder.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stanga.movieFinder.R
import com.stanga.movieFinder.databinding.ActivityMovieDetailsBinding
import com.stanga.movieFinder.model.Movie
import com.stanga.movieFinder.viewmodel.MovieFinderViewModel

class MovieDetailsActivity: AppCompatActivity() {
    private val viewModel by viewModels<MovieFinderViewModel>()
    private var movie: Movie? = null
    private lateinit var binding: ActivityMovieDetailsBinding

    companion object {
        const val kMovie = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_details)

        movie = intent.getParcelableExtra(kMovie)
        binding.movieFinderViewModel = viewModel
        binding.movie = movie
    }

    fun onFavouriteTap(view: View) {
        movie?.let {
            viewModel.toggleMovieFavourite(it)
            MovieFinderViewModel.loadFavouriteStatus(binding.ivFavouriteStatus, it)
        }
    }
}