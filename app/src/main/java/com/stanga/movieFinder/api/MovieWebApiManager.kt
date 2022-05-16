package com.stanga.movieFinder.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieWebApiManager() {
    val movieWebApi: MovieWebApi

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        this.movieWebApi = retrofit.create(MovieWebApi::class.java)
    }
}