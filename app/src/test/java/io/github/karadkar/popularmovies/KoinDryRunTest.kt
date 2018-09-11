package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.appModules
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class KoinDryRunTest : KoinTest {
    @Test
    fun dependencyGraphDryRun() {
        checkModules(appModules)
    }
}