package io.github.karadkar.popularmovies.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIE ORDER BY id ASC")
    fun getAll(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM MOVIE ORDER BY popularity DESC")
    fun getPopular(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movies: List<MovieEntity>)

    @Query("SELECT * FROM MOVIE WHERE id=:movieId")
    fun findById(movieId: Int): Flowable<MovieEntity>
}