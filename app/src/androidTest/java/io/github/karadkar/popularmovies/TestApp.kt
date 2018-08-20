package io.github.karadkar.popularmovies

import android.app.Application
import io.github.karadkar.popularmovies.data.appModules
import org.koin.android.ext.android.startKoin

// needs testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner" to defaultConfig
// Application class for Instrumentation test
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(context = this, modules = appModules)
    }
}