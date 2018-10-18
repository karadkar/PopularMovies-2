package io.github.karadkar.popularmovies.utils.extensions

import io.github.karadkar.popularmovies.utils.RxScheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Completable.performOnBack(scheduler: RxScheduler) = this.subscribeOn(scheduler.workerThread())

fun <T> Flowable<T>.performOnBackObserverOnMain(scheduler: RxScheduler): Flowable<T> {
    return this.subscribeOn(scheduler.workerThread())
            .observeOn(scheduler.mainThread())
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}
