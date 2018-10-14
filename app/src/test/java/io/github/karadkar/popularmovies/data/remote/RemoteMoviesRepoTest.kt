package io.github.karadkar.popularmovies.data.remote

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.remote.models.TmdbResult
import io.github.karadkar.popularmovies.utils.MovieEntityDataFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RemoteMoviesRepoTest {

    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    private lateinit var repo: MoviesDataContract.Remote
    private lateinit var service: TmdbMoviesApi
    @Before
    fun setup() {
        service = mock(TmdbMoviesApi::class.java)
        repo = RemoteMoviesRepo(service, "api-key")
    }


    @Test
    fun getPopularMovies() {
        val tmdbPage = TmdbResult()
        tmdbPage.results = MovieEntityDataFactory.getTmdbResults()

        Mockito.`when`(service.getPopularMovies(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(tmdbPage))

        repo.getPopularMovies().test().assertValues(tmdbPage.results)
    }
}