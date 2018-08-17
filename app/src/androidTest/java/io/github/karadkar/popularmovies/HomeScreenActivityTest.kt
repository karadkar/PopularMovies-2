package io.github.karadkar.popularmovies

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest {
    @get:Rule
    val rule = ActivityTestRule(HomeScreenActivity::class.java)

    @Test
    fun shouldHaveTextView() {
        onView(withId(R.id.tv_homescreen_message))
                .check(matches(isDisplayed()))
    }
}