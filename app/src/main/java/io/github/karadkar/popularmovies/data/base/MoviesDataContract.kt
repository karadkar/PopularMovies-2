package io.github.karadkar.popularmovies.data.base

import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface MoviesDataContract {
    interface Local {
        fun getPopularMovies(): Flowable<List<MovieListItem>>
        fun setBookmarked(movieId: Int): Completable
        fun setUnBookmarked(movieId: Int): Completable
        fun saveMovies(movies: List<Movie>): Completable
    }

    interface Remote {
        fun getPopularMovies(): Single<List<Movie>>
    }

    interface Repository {
        val result: PublishSubject<Outcome<List<MovieListItem>>>
        fun getPopularMovies()
    }
}