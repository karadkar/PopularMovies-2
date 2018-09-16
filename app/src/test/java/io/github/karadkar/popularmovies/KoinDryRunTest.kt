package io.github.karadkar.popularmovies

import io.github.karadkar.popularmovies.data.appModules
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
class KoinDryRunTest : KoinTest {
    @Test
    fun dependencyGraphDryRun() {
        checkModules(appModules)
    }
}