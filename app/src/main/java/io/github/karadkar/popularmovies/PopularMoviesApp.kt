package io.github.karadkar.popularmovies

import android.app.Application
import io.github.karadkar.popularmovies.data.appModules
import org.koin.android.ext.android.startKoin

class PopularMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // setup koin
        startKoin(this, appModules)
    }
}