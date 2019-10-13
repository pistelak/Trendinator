package com.skypicker.trendinator.networking

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

data class MovieNetworkModel(
    val title: String,
    val year: Int,
    val image: String
)

interface IMovieEngine {

    fun downloadMovieDetails(): Single<List<MovieNetworkModel>>
}

class MovieEngine : IMovieEngine, KoinComponent {

    private val traktTVService: TraktTVService by inject()
    private val movieDBService: MovieDBService by inject()

    override fun downloadMovieDetails() =
        traktTVService.downloadMovies()
            .flatMapObservable { movies -> Observable.fromIterable(movies) }
            .flatMapSingle { movie ->
                Log.d(this.javaClass.name, "${movie.movie.ids.tmdb}")
                movieDBService.downloadDetail(movie.movie.ids.tmdb)
                    .map {
                        MovieNetworkModel(
                            title = movie.movie.title,
                            year = movie.movie.year,
                            image = it.poster_path
                        )
                    }
            }
            .toList()

}