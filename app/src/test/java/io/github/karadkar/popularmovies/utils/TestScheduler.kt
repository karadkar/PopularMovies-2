package io.github.karadkar.popularmovies.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestScheduler : RxScheduler {
    override fun mainThread(): Scheduler = Schedulers.trampoline()
    override fun workerThread(): Scheduler = Schedulers.trampoline()
}