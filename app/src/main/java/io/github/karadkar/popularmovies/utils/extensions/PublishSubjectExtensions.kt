package io.github.karadkar.popularmovies.utils.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.github.karadkar.popularmovies.data.base.Outcome
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

// convert publish subject to live data by subscribing to it
fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val liveData = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe { t: T -> liveData.value = t })
    return liveData
}

// push failed events
fun <T> PublishSubject<Outcome<T>>.failed(t: Throwable) {
    with(this) {
        this.loading(false)
        onNext(Outcome.failure(t))
    }
}

// push success events
fun <T> PublishSubject<Outcome<T>>.success(data: T) {
    with(this) {
        this.loading(false)
        onNext(Outcome.success(data))
    }
}

// push progress events
fun <T> PublishSubject<Outcome<T>>.loading(isLoading: Boolean) {
    this.onNext(Outcome.loading(isLoading))
}