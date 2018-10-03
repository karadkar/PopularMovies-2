package io.github.karadkar.popularmovies.data.local

import io.github.karadkar.popularmovies.data.base.Movie
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.mapper.EntityMapper
import io.github.karadkar.popularmovies.utils.RxScheduler
import io.github.karadkar.popularmovies.utils.extensions.performOnBack
import io.reactivex.Completable
import io.reactivex.Flowable

class MoviesLocalData(private val db: MovieDatabase,
                      private val mapper: EntityMapper<Movie, MovieEntity>,
                      private val scheduler: RxScheduler) : MoviesDataContract.Local {
    override fun getPopularMovies(): Flowable<List<Movie>> {
        return db.movieDao().getPopular().map { it -> it }
    }

    override fun setBookmarked(movieId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUnBookmarked(movieId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return Completable.fromAction {
            db.movieDao().saveOrUpdate(
                    movies.map { mapper.mapFromEntiry(it) }
            )
        }.performOnBack(scheduler)
    }
}
