package io.github.karadkar.popularmovies.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import io.github.karadkar.popularmovies.utils.MovieEntityDataFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class MovieDatabaseTest {

    //allows test to run synchronously
    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    lateinit var database: MovieDatabase

    @Before
    fun setupDb() {
        database = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.applicationContext, MovieDatabase::class.java)
                .allowMainThreadQueries().build()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun saveMovies() {
        // list is sorted by movie.id
        val movies = MovieEntityDataFactory.getMovieEntities(50)
        database.movieDao().save(movies)

        val testSub = database.movieDao().getAll().test()
        testSub.assertValue(movies)
    }

    @Test
    fun getMovieById() {
        val movies = MovieEntityDataFactory.getMovieEntities(5)
        database.movieDao().save(movies)

        val movie = movies[2]
        database.movieDao().findById(movieId = movie.id)
                .test().assertValue(movie)
    }
}