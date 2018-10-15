package io.github.karadkar.popularmovies.data.local.db

import android.arch.persistence.room.*
import io.github.karadkar.popularmovies.data.local.models.MovieEntity
import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIE ORDER BY id ASC")
    fun getAll(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM MOVIE ORDER BY popularity DESC")
    fun getPopular(): Flowable<List<MovieEntity>>

    @Query("SELECT movie.id As id, movie.title As title, movie.posterPath As posterPath, " +
            "movie.voteAverage As votes, bookmarks.bookmarked As bookmarked " +
            "FROM MOVIE LEFT JOIN BOOKMARKS On movie.id = bookmarks.movieId ORDER BY movie.popularity DESC")
    fun getPopularList(): Flowable<List<MovieListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrUpdate(movies: List<MovieEntity>)

    @Query("SELECT * FROM MOVIE WHERE id=:movieId")
    fun findById(movieId: Int): Flowable<MovieEntity>

    @Delete
    fun deleteMovies(movies: List<MovieEntity>)
}