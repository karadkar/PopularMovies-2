package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.appModules
import io.github.karadkar.popularmovies.utils.AppConstants
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.koin.test.dryRun

class KoinDryRunTest : KoinTest {
    @Test
    fun dependencyGraphDryRun() {
        // dummy properties
        val params = mapOf(AppConstants.KEY_MOVIE_NAME to "dummy string")

        startKoin(list = appModules)
        dryRun()
    }
}