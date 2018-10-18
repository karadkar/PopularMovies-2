package io.github.karadkar.popularmovies.data

import io.github.karadkar.popularmovies.data.base.MoviesDataContract
import io.github.karadkar.popularmovies.data.base.Outcome
import io.github.karadkar.popularmovies.data.local.models.MovieListItem
import io.github.karadkar.popularmovies.utils.RxScheduler
import io.github.karadkar.popularmovies.utils.extensions.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MoviesRepository(
        val local: MoviesDataContract.Local,
        val remote: MoviesDataContract.Remote,
        val scheduler: RxScheduler,
        val compositeDisposable: CompositeDisposable
) : MoviesDataContract.Repository {

    override val result: PublishSubject<Outcome<List<MovieListItem>>> = PublishSubject.create<Outcome<List<MovieListItem>>>()

    override fun getPopularMovies() {
        local.getPopularMovies()
                .doOnSubscribe { result.loading(true) }
                .performOnBackObserverOnMain(scheduler)
                .subscribe({
                    result.loading(false)
                    result.success(it)
                }, {
                    result.loading(false)
                    result.failed(it)
                })
                .addTo(compositeDisposable)
    }
}
