package io.github.karadkar.popularmovies

import android.arch.lifecycle.ViewModel
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : KoinTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeScreenActivity::class.java, true, true)

    val viewModel: MovieListViewModel by inject()


    @Before
    fun setup() {
        declareMock<MovieListViewModel>(isFactory = true, binds = listOf(ViewModel::class))
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun shouldHaveTextViewVisible() {

        `when`(viewModel.sayHello())
                .thenReturn("hello view-model")

        onView(withId(R.id.tv_homescreen_message))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_homescreen_message))
                .check(matches(withText("hello view-model")))
    }
}