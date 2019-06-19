package com.example.javadevsnairobi

import android.content.pm.ActivityInfo
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


import androidx.test.espresso.ViewAction

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.Espresso.onData
import com.googlecode.eyesfree.utils.LogUtils.TAG
import org.hamcrest.CoreMatchers.anything

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Rule
    @JvmField
    var mMainActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        try {
            Thread.sleep(6000)
        } catch (e: Exception) {
            Log.e(TAG, "Test setUp: " + e.message)
        }

    }


    private fun loadIdle() {
        val mainActivityIdlingResource = mMainActivityTestRule.activity.idlingResourceInTest
        IdlingRegistry.getInstance().register(mainActivityIdlingResource)
    }

    @Test
    fun clickOnSingleUserItemOpensDetailActivity() {

        loadIdle()
        onView(withId(R.id.my_recycler_view))
                .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.username_detail)).check(matches(withText("k33ptoo")))
    }

    @Test
    fun testRecyclerView() {
        loadIdle()
        onView(withId(R.id.my_recycler_view))
                .check(matches(isDisplayed()))

    }


    @Test
    fun testToolbar() {
        loadIdle()
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()))

    }

    @Test
    fun testSwipeRefreshLayout() {
        loadIdle()
        onView(withId(R.id.swipeRefresh)).check(matches(isDisplayed()))

    }

    @Test
    fun testMainActivityLayout() {
        loadIdle()
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()))

    }

    @Test
    fun testRecyclerViewItemClicked() {
        loadIdle()
        onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(5))

        onView(withId(R.id.my_recycler_view)).perform(actionOnItemAtPosition<ViewHolder>(5, click()))
    }

    @Test
    fun testSearchUserText() {
        loadIdle()
        onView(withId(R.id.txtSearch)).check(matches(isDisplayed()))

    }

    @Test
    fun testCoordinatorLayout() {
        loadIdle()
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()))

    }


}
