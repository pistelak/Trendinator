package com.skypicker.trendinator.DI

import com.skypicker.trendinator.networking.IMovieEngine
import com.skypicker.trendinator.networking.MovieDBService
import com.skypicker.trendinator.networking.MovieEngine
import com.skypicker.trendinator.networking.TraktTVService
import com.skypicker.trendinator.platform.IImageLoader
import com.skypicker.trendinator.platform.ImageLoader
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {

    fun retrofit(baseURL: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        retrofit("https://api.trakt.tv/").create<TraktTVService>(TraktTVService::class.java)
    }

    single {
        retrofit("https://api.themoviedb.org/3/").create<MovieDBService>(MovieDBService::class.java)
    }
}

val platformModule = module {

    single<IImageLoader> {
        ImageLoader()
    }
}

val appModule = module {

    single<IMovieEngine> {
        MovieEngine()
    }
}