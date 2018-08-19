package io.github.karadkar.popularmovies

import android.app.Application

// needs testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner" to defaultConfig
// Application class for Instrumentation test
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin(context = this, modules = testAppModules)
    }
}