package io.github.karadkar.popularmovies

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : KoinTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeScreenActivity::class.java, true, false)

    lateinit var mockVm: MovieListViewModel


    @Before
    fun setup() {
        mockVm = mock(MovieListViewModel::class.java)

        /**
         * As Activity only requires viewModel.
         * also note, we override Application class as TestApp to avoid modules from main app.
         */
        loadKoinModules(module {
            viewModel {
                mockVm
            }
        })
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun shouldHaveTextViewWithMessage() {
        // 1. declare mock method
        val message = "hello view-model"
        Mockito.`when`(mockVm.sayHello())
                .thenReturn(message)

        // 2. start activity
        rule.launchActivity(null)


        // 3. test
        assertDisplayed(R.id.tv_homescreen_message) // with id
        assertDisplayed(message) // with string
    }
}