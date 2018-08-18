package io.github.karadkar.popularmovies

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.karadkar.popularmovies.data.MovieListRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : KoinTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeScreenActivity::class.java, true, true)

    private val viewModelMock: MovieListViewModel = mock()

    private val repositoryMock: MovieListRepository = mock()

    @Before
    fun setup() {

        /*loadKoinModules(applicationContext {
            bean { repositoryMock }
            viewModel<MovieListViewModel> { viewModelMock }
        })*/

        whenever(viewModelMock.sayHello())
                .thenReturn("hello view-model")

        whenever(repositoryMock.giveHello())
                .thenReturn("hello repository")
    }

    @After
    fun cleanUp() {
        closeKoin()
    }

    @Test
    fun shouldHaveTextView() {
        onView(withId(R.id.tv_homescreen_message))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_homescreen_message))
                .check(matches(withText("hello view-model")))
    }
}