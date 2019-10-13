package com.skypicker.trendinator.networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class MovieDBModel(
    val poster_path: String
)

interface MovieDBService {

    @GET("movie/{movie_id}")
    fun downloadDetail(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String = "c3c94539b01de1e75cc8621a72893a2c"
    ): Single<MovieDBModel>

}