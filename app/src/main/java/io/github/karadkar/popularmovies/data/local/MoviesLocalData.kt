package io.github.karadkar.popularmovies.data.local

import io.github.karadkar.popularmovies.data.base.Movie
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.local.db.MovieDatabase
import io.github.karadkar.popularmovies.data.local.models.BookmarkEntity
import io.github.karadkar.popularmovies.data.local.models.MovieEntity
import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.github.karadkar.popularmovies.data.mapper.EntityMapper
import io.github.karadkar.popularmovies.utils.RxScheduler
import io.github.karadkar.popularmovies.utils.extensions.performOnBack
import io.reactivex.Completable
import io.reactivex.Flowable

class MoviesLocalData(private val db: MovieDatabase,
                      private val mapper: EntityMapper<Movie, MovieEntity>,
                      private val scheduler: RxScheduler) : MoviesDataContract.Local {
    override fun getPopularMovies(): Flowable<List<MovieListItem>> {
        return db.movieDao().getPopularList()
    }

    override fun setBookmarked(movieId: Int): Completable {
        return Completable.fromAction {
            db.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movieId, bookmarked = true))
        }.performOnBack(scheduler)
    }

    override fun setUnBookmarked(movieId: Int): Completable {
        return Completable.fromAction {
            db.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movieId, bookmarked = false))
        }.performOnBack(scheduler)
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return Completable.fromAction {
            db.movieDao().saveOrUpdate(
                    movies.map { mapper.mapFromEntiry(it) }
            )
        }.performOnBack(scheduler)
    }
}
