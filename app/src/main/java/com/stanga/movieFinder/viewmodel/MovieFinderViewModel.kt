package com.stanga.movieFinder.viewmodel

import android.content.Context
import android.text.Editable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.stanga.movieFinder.App
import com.stanga.movieFinder.R
import com.stanga.movieFinder.model.Movie
import com.stanga.movieFinder.model.MovieSearchResponse
import com.stanga.movieFinder.storage.FavouriteMoviesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieFinderViewModel : ViewModel() {

    private var timer: Timer = Timer()
    private var latestSearchPhrase: String = ""
    private var searchInProgress: Boolean = false
    var searchResults: MutableLiveData<List<Movie>> = MutableLiveData()

    fun onTextChange(et: Editable) {
        timer.cancel()
        val phrase = et.toString()
        latestSearchPhrase = phrase
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                callSearch(phrase)
            }
        }, 500)
    }

    private fun callSearch(phrase: String) {
        if (searchInProgress) {
            return
        }
        searchInProgress = true

        App.app?.movieWebApiManager?.movieWebApi?.searchForMovies("9278e7bc", phrase)
            ?.enqueue(object : Callback<MovieSearchResponse> {
                override fun onResponse(
                    call: Call<MovieSearchResponse>,
                    response: Response<MovieSearchResponse>
                ) {
                    searchInProgress = false
                    if (phrase != latestSearchPhrase) {
                        callSearch(latestSearchPhrase)
                    } else {
                        response.body()?.movies?.let {
                            searchResults.postValue(it)
                        }
                    }
                }

                override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                    t.printStackTrace()
                    searchInProgress = false
                }
            })
    }

    fun toggleMovieFavourite(movie: Movie) {
        val manager = FavouriteMoviesManager()
        if (manager.isMovieFavourite(movie)) {
            manager.removeFromFavourites(movie)
        } else {
            manager.addToFavourites(movie)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("moviePosterImage")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }

        @JvmStatic
        @BindingAdapter("movieFavouriteStatus")
        fun loadFavouriteStatus(view: ImageView, movie: Movie) {
            val manager = FavouriteMoviesManager()
            if (manager.isMovieFavourite(movie)) {
                view.setImageResource(R.drawable.star)
            } else {
                view.setImageResource(R.drawable.star_off)
            }
        }

    }

}