package com.aripratom.footballleaguesubmission2.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aripratom.footballleaguesubmission2.MainActivity
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppViewBehaviour() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.scheduleSearch)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withId(R.id.search)).perform(ViewActions.typeText("Juventus"))
        Thread.sleep(2000)
        onView(withId(R.id.rv_schedule_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Thread.sleep(2000)
    }
}