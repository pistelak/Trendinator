package com.skypicker.trendinator.networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

data class TraktTVMovieNetworkModel(
    val movie: Movie
) {

    data class Movie(
        val title: String,
        val year: Int,
        val ids: AnotherSources
    )

    data class AnotherSources(
        val tmdb: Int
    )
}

interface TraktTVService {

    @GET("movies/trending")
    @Headers(
        "trakt-api-version:2",
        "trakt-api-key:612b4e8a43e66d05e006fbd4ba7f4788acfa66639594e83a2bbfdb2d66491d2d"
    )
    fun downloadMovies(): Single<List<TraktTVMovieNetworkModel>>
}