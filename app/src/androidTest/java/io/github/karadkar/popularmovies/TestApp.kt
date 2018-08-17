package io.github.karadkar.popularmovies

import android.app.Application

// Application call for Instrumentation test
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        /*
        start koin for test
        startKoin(this, testAppModules)
        loadKoinModules(testAppModules)
        */
    }
}