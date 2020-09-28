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

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testAppViewBehaviour(){
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.rv_legue)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        Thread.sleep(3000)
        onView(withId(R.id.navigation_schedule)).perform(ViewActions.click())
        Thread.sleep(3000)
        /*
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_schedule)).perform(click())
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.rv_schedule_search)).perform(typeTextIntoFocusedView("juventus"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))


              Thread.sleep(100)
              onView(withId(R.id.rv_schedule_previous)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
              Thread.sleep(3000)
              onView(withId(R.id.rv_schedule_previous)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(13, click()))
              Thread.sleep(3000)
              onView(withId(R.id.add_to_favorite)).perform(click())
              Thread.sleep(1000)

              pressBack()

              onView(withId(R.id.navigation_schedule)).check(matches(isDisplayed()))

              Thread.sleep(100)
              onView(withId(R.id.rv_schedule_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
              Thread.sleep(3000)
              onView(withId(R.id.rv_schedule_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(13, click()))
              Thread.sleep(3000)
              onView(withId(R.id.add_to_favorite)).perform(click())
              Thread.sleep(1000)

              pressBack()

              onView(withId(R.id.navigation_league)).perform(click())
              Thread.sleep(100)
              onView(withId(R.id.rv_legue)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
              Thread.sleep(3000)
              onView(withId(R.id.rv_legue)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
              Thread.sleep(3000)
              //onView(withId(R.id.add_to_favorite)).perform(click())
              //Thread.sleep(1000)

              pressBack()



              onView(withId(R.id.navigation_favorite)).perform(click())
              Thread.sleep(100)
              onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
              Thread.sleep(3000)
              onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
              Thread.sleep(3000)
              onView(withId(R.id.add_to_favorite)).perform(click())
              Thread.sleep(1000)

              pressBack()

              Thread.sleep(500)

          */
    }
}