package com.skypicker.trendinator.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skypicker.trendinator.model.Movie
import com.skypicker.trendinator.networking.IMovieEngine
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel: ViewModel(), KoinComponent {

  val movies: MutableLiveData<List<Movie>> = MutableLiveData()

  private val engine: IMovieEngine by inject()

  init {

    val disposable = engine.downloadMovieDetails()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe( { n ->
        val results = n.map { Movie(title = it.title, year = it.year, posterPath = it.image) }
        movies.postValue(results)
      },
        { e ->
          Log.e(this.javaClass.name, e.message, e)
        }
      )

  }

}