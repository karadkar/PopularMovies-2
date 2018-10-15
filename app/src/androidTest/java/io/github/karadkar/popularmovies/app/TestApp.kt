package io.github.karadkar.popularmovies.app

import android.app.Application
import org.koin.android.ext.android.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        /**
         * Not initialising any modules.
         * Tests will decide which module definitions to load, override or mock
         */
        startKoin(this, emptyList())
    }
}