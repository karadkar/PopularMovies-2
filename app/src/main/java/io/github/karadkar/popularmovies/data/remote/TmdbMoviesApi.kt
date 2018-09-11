package io.github.karadkar.popularmovies.data.remote

import io.github.karadkar.popularmovies.data.remote.models.TmdbResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface TmdbMoviesApi {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<TmdbResult>

}