package com.stanga.movieFinder.storage

import android.content.Context
import com.stanga.movieFinder.App
import com.stanga.movieFinder.model.Movie
import java.util.*

class FavouriteMoviesManager {
    private val kFavouritesList = "listFavourites"

    fun isMovieFavourite(movie: Movie): Boolean {
        return favouriteMovies().contains(movie.imdbID)
    }

    private fun favouriteMovies(): LinkedList<String> {
        val list = LinkedList<String>()
        App.app?.getSharedPreferences(kFavouritesList, Context.MODE_PRIVATE)
            ?.getString(kFavouritesList, "")?.let { csv ->
                val items = csv.split(",")
                items.forEach {
                    list.add(it)
                }
            }
        return list
    }
    fun removeFromFavourites(movie: Movie) {
        val favourites = favouriteMovies()
        favourites.remove(movie.imdbID)
        val newString = favourites.joinToString(",")
        App.app?.getSharedPreferences(kFavouritesList, Context.MODE_PRIVATE)?.edit()?.putString(
            kFavouritesList, newString
        )?.commit()
    }

    fun addToFavourites(movie: Movie) {
        val favourites = favouriteMovies()
        if (!favourites.contains(movie.imdbID)) {
            favourites.add(movie.imdbID)
        }
        val newString = favourites.joinToString(",")
        App.app?.getSharedPreferences(kFavouritesList, Context.MODE_PRIVATE)?.edit()?.putString(
            kFavouritesList, newString
        )?.commit()
    }
}