package io.github.karadkar.popularmovies

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.github.karadkar.popularmovies.data.MovieListRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : KoinTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeScreenActivity::class.java, true, true)

    private val viewModelMock: MovieListViewModel by inject()
    private lateinit var repositoryMock: MovieListRepository

    @Before
    fun setup() {
        Mockito.`when`(viewModelMock.sayHello())
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