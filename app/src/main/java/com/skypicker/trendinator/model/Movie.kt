package com.skypicker.trendinator.model

data class Movie(
    val title: String,
    val year: Int,
    val posterPath: String
) {

    val subtitle: String
        get() = year.toString()

    val posterURL: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"

}


