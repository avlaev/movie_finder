package com.stanga.movieFinder.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.stanga.movieFinder.R
import com.stanga.movieFinder.adapter.MoviesAdapter
import com.stanga.movieFinder.databinding.ActivityMainBinding
import com.stanga.movieFinder.viewmodel.MovieFinderViewModel


class MovieFinderActivity: AppCompatActivity() {
    private val viewModel by viewModels<MovieFinderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = MoviesAdapter()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.movieFinderViewModel = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.movieClicked = {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.kMovie, it)
            startActivity(intent)
        }

        viewModel.searchResults.observe(this
        ) { movies ->
            adapter.setContent(movies)
        }
    }
}