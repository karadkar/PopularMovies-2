package io.github.karadkar.popularmovies.utils

import io.reactivex.Scheduler

interface RxScheduler {
    fun mainThread(): Scheduler
    fun workerThread(): Scheduler
}