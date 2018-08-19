package io.github.karadkar.popularmovies

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
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : KoinTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeScreenActivity::class.java, true, true)

    @Before
    fun setup() {
        declareMock<MovieListViewModel>()
        Mockito.`when`(get<MovieListViewModel>().sayHello())
                .thenReturn("hello view-model")
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun shouldHaveTextView() {
        onView(withId(R.id.tv_homescreen_message))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_homescreen_message))
                .check(matches(withText("hello view-model")))
    }
}