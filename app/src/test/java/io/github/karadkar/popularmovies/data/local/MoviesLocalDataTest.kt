package io.github.karadkar.popularmovies.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
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
class MoviesLocalDataTest : KoinTest {
    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    lateinit var localRepo: MoviesDataContract.Local
    lateinit var db: MovieDatabase
    lateinit var movieEntities: List<MovieEntity>
    @Before
    fun setupDb() {
        startKoin(robolectricModules)
        localRepo = get()
        db = get()
        movieEntities = MovieEntityDataFactory.getMovieEntities(50)
    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun getPopularMovies() {
        db.movieDao().saveOrUpdate(movieEntities)
        localRepo.getPopularMovies().test().assertValue(movieEntities.sortedByDescending { it.popularity })
    }

    @Test
    fun setBookmarked() {
    }

    @Test
    fun setUnBookmarked() {
    }

    @Test
    fun saveMovies() {
        // verify that it's saved to database
        localRepo.saveMovies(movieEntities).test().assertComplete()

        // verify that we can retrieve same movies
        db.movieDao().getPopular().test().assertValues(movieEntities.sortedByDescending { it.popularity })

    }
}