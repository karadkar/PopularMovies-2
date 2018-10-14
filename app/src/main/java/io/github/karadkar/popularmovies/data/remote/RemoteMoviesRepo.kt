package io.github.karadkar.popularmovies.data.remote

import io.github.karadkar.popularmovies.data.base.Movie
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.reactivex.Single

class RemoteMoviesRepo(private val service: TmdbMoviesApi, private val apiKey: String) : MoviesDataContract.Remote {
    override fun getPopularMovies(): Single<List<Movie>> {
        return service.getPopularMovies(apiKey)
                .map {
                    it.results
                }
    }
}