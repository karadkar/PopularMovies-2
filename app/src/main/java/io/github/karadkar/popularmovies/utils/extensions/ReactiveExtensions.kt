package io.github.karadkar.popularmovies.utils.extensions

import io.github.karadkar.popularmovies.utils.RxScheduler
import io.reactivex.Completable

fun Completable.performOnBack(scheduler: RxScheduler) = this.subscribeOn(scheduler.workerThread())
