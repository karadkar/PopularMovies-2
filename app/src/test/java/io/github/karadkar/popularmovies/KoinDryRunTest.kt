package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.appModules
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.koin.test.dryRun

class KoinDryRunTest : KoinTest {
    @Test
    fun dependencyGraphDryRun() {
        startKoin(list = appModules)
        dryRun { parametersOf("dummy params") }
    }
}