package com.skypicker.trendinator.ui.main

import android.view.*
import androidx.databinding.*
import androidx.recyclerview.widget.*
import com.skypicker.trendinator.*
import com.skypicker.trendinator.databinding.*
import com.skypicker.trendinator.model.*
import com.skypicker.trendinator.platform.IImageLoader
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(), KoinComponent {

  class MovieViewHolder(val binding: ItemMoviesAdapterBinding): RecyclerView.ViewHolder(
    binding.root)

  private val imageLoader: IImageLoader by inject()

  var movies = listOf<Movie>()
    set(value) {
      field = value
      notifyItemRangeInserted(0, movies.size - 1)
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

    val binding = DataBindingUtil.inflate<ItemMoviesAdapterBinding>(
      LayoutInflater.from(parent.context),
      R.layout.item_movies_adapter,
      parent,
      false
    )

    return MovieViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return movies.size
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    val movie = movies[position]

    holder.binding.movie = movie

    imageLoader.loadImage(
      holder.binding.root.context,
      movie.posterURL,
      holder.binding.imageView
    )

    holder.binding.executePendingBindings()
  }
}