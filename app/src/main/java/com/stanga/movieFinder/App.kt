package com.stanga.movieFinder

import android.app.Application
import com.stanga.movieFinder.api.MovieWebApiManager

class App : Application() {
    val movieWebApiManager = MovieWebApiManager()

    companion object {
        var app: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}