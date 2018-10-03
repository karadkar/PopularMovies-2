package io.github.karadkar.popularmovies.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.karadkar.popularmovies.robolectricModules
import io.github.karadkar.popularmovies.utils.MovieEntityDataFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDatabaseTest : KoinTest {

    // [imp] allows test to run synchronously
    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    lateinit var database: MovieDatabase

    @Before
    fun setupDb() {
        startKoin(robolectricModules)
        database = get()
    }

    @After
    fun close() {
        stopKoin()
    }

    @Test
    fun saveMovies() {
        // list is sorted by movie.id
        val movies = MovieEntityDataFactory.getMovieEntities(50)
        database.movieDao().saveOrUpdate(movies)

        // allAll return movies sorted by id
        val testSub = database.movieDao().getAll().test()

        testSub.assertNever(movies.sortedByDescending { it.id })
        testSub.assertValue(movies.sortedBy { it.id })
    }

    @Test
    fun updateMovie() {
        val movies = MovieEntityDataFactory.getMovieEntities(5)

        database.movieDao().saveOrUpdate(movies)
        database.movieDao().saveOrUpdate(movies) // this should not fail
        database.movieDao().getAll().test().assertValue { it.size == 5 }
    }

    @Test
    fun getMovieById() {
        val movies = MovieEntityDataFactory.getMovieEntities(5)
        database.movieDao().saveOrUpdate(movies)

        val movie = movies[2]
        database.movieDao().findById(movieId = movie.id)
                .test().assertValue(movie)
    }

    @Test
    fun getPopularMovies() {
        // popularity is decided by movie.popularity rank. (higher is better)
        val movies = MovieEntityDataFactory.getMovieEntities(50)
        database.movieDao().saveOrUpdate(movies)

        val testSub = database.movieDao().getPopular().test()

        // assert that movies are sorted by descending order of popularity
        testSub.assertNever(movies)
        testSub.assertValue(movies.sortedByDescending { it.popularity })
    }

    @Test
    fun updateBookmark() {
        val movies = MovieEntityDataFactory.getMovieEntities(50)
        database.movieDao().saveOrUpdate(movies)
        val movie = movies[10]

        // add as bookmarked
        database.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movie.id, bookmarked = true))
        database.bookmarkDao().getBookmark(movieId = movie.id)
                .test().assertValue { it.movieId == movie.id && it.bookmarked }

        // remove as bookmarked
        database.bookmarkDao().updateBookmark(BookmarkEntity(movieId = movie.id, bookmarked = false))
        database.bookmarkDao().getBookmark(movieId = movie.id)
                .test().assertValue { it.movieId == movie.id && !it.bookmarked }
    }
}