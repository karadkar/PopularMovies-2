package io.github.karadkar.popularmovies

import android.app.Application
import io.github.karadkar.popularmovies.data.appModules
import org.koin.android.ext.android.startKoin

// Application class for Instrumentation test
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(context = this, modules = appModules)
    }
}