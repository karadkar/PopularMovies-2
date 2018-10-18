package io.github.karadkar.popularmovies.data

import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.base.Outcome
import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.github.karadkar.popularmovies.utils.MovieEntityDataFactory
import io.github.karadkar.popularmovies.utils.TestScheduler
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito
import org.mockito.Mockito.mock


class MoviesRepositoryTest : AutoCloseKoinTest() {

    lateinit var local: MoviesDataContract.Local
    lateinit var remote: MoviesDataContract.Remote
    lateinit var repo: MoviesDataContract.Repository

    @Before
    fun setup() {
        local = mock(MoviesDataContract.Local::class.java)
        remote = mock(MoviesDataContract.Remote::class.java)
        repo = MoviesRepository(local, remote, TestScheduler(), CompositeDisposable())
    }

    @Test
    fun getPopularMovies() {
        val movieListItems = MovieEntityDataFactory.getMovieListItems(10)
        val obs = TestObserver<Outcome<List<MovieListItem>>>()

        Mockito.`when`(local.getPopularMovies())
                .thenReturn(Flowable.just(movieListItems))

        // subscribe to result before making call so we receive all events from [PublishSubject]
        repo.result.subscribe(obs)
        obs.assertEmpty()

        // fetch
        repo.getPopularMovies()

        // verify
        Mockito.verify(local).getPopularMovies()
        obs.assertValueAt(0, Outcome.loading(true))
        obs.assertValueAt(1, Outcome.loading(false))
        obs.assertValueAt(2, Outcome.success(movieListItems))
    }
}