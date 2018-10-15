package io.github.karadkar.popularmovies.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.local.db.MovieDatabase
import io.github.karadkar.popularmovies.data.local.models.MovieEntity
import io.github.karadkar.popularmovies.data.robolectricModules
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

        // sort by popularity
        movieEntities = movieEntities.sortedByDescending { it.popularity }

        localRepo.getPopularMovies().test().apply {
            assertNoErrors()
            values()[0].forEachIndexed { index, movieListItem ->
                // compare movie list item with entity
                assert(movieListItem.equals(movieEntities[index]))
            }
        }
    }

    @Test
    fun updateBookmarkShouldFail() {
        // update bookmark should fail with invalid movie id
        // also happens when movie is not in database
        localRepo.setBookmarked(9999).test().assertNotComplete()
        localRepo.setUnBookmarked(9999).test().assertNotComplete()
    }

    @Test
    fun setBookmarked() {
        val movie = MovieEntityDataFactory.getMovieEntity()

        // save movie
        db.movieDao().saveOrUpdate(listOf(movie))

        // verify bookmarked
        localRepo.setBookmarked(movie.id).test().assertComplete()
        db.bookmarkDao().getBookmark(movie.id).test().assertValue { it.bookmarked }
    }

    @Test
    fun setUnBookmarked() {
        val movie = MovieEntityDataFactory.getMovieEntity()

        // save movie
        db.movieDao().saveOrUpdate(listOf(movie))

        // verify un-bookmarked
        localRepo.setUnBookmarked(movie.id).test().assertComplete()
        db.bookmarkDao().getBookmark(movie.id).test().assertValue { !it.bookmarked }
    }

    @Test
    fun bookmarkOperation() {
        val movie = MovieEntityDataFactory.getMovieEntity()

        // save movie
        db.movieDao().saveOrUpdate(listOf(movie))

        // verify set-bookmarked
        localRepo.setBookmarked(movie.id).test().assertComplete()
        db.bookmarkDao().getBookmark(movie.id).test().assertValue { it.bookmarked }

        // verify un-bookmarked
        localRepo.setUnBookmarked(movie.id).test().assertComplete()
        db.bookmarkDao().getBookmark(movie.id).test().assertValue { !it.bookmarked }
    }

    @Test
    fun saveMovies() {
        // verify that it's saved to database
        localRepo.saveMovies(movieEntities).test().assertComplete()

        // verify that we can retrieve same movies
        db.movieDao().getPopular().test().assertValues(movieEntities.sortedByDescending { it.popularity })

    }
}