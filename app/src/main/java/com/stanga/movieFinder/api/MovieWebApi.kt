package com.stanga.movieFinder.api

import com.stanga.movieFinder.model.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieWebApi {
    @GET("/")
    fun searchForMovies(
        @Query("apikey") apikey: String,
        @Query("s") phrase: String
    ): Call<MovieSearchResponse>
}