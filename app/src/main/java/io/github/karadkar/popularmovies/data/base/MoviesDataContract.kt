package io.github.karadkar.popularmovies.data.base

import io.reactivex.Completable
import io.reactivex.Flowable

interface MoviesDataContract {
    interface Local {
        fun getPopularMovies(): Flowable<List<Movie>>
        fun setBookmarked(movieId: Int): Completable
        fun setUnBookmarked(movieId: Int): Completable
        fun saveMovies(movies: List<Movie>): Completable
    }
}