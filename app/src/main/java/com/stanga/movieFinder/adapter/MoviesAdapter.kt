package com.stanga.movieFinder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stanga.movieFinder.databinding.ViewMovieBinding
import com.stanga.movieFinder.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private var list: List<Movie> = listOf()
    private var layoutInflater: LayoutInflater? = null
    var movieClicked: ((Movie) -> Unit) = {}

    @SuppressLint("NotifyDataSetChanged")
    fun setContent(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = ViewMovieBinding.inflate(layoutInflater!!, parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            movieClicked(list[position])
        }
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MovieViewHolder(private val binding: ViewMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
    }

}