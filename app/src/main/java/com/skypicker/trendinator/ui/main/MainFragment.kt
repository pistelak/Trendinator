package com.skypicker.trendinator.ui.main

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.skypicker.trendinator.databinding.*

class MainFragment: Fragment() {

  companion object {
    fun newInstance() = MainFragment()
  }

  private lateinit var binding: MainFragmentBinding

  private lateinit var viewModel: MainViewModel
  private val adapter = MoviesAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = MainFragmentBinding.inflate(inflater)
    binding.moviesList.layoutManager = LinearLayoutManager(context)
    binding.moviesList.adapter = adapter

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    viewModel.movies.observe(this, Observer{
      adapter.movies = it
    })

    binding.viewModel = viewModel
  }
}