package com.softradix.techchallenge;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest{

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkIfActivityVisible(){
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfActivityRecyclerViewVisible(){
        onView(withId(R.id.stores_view)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfActivityRecyclerViewClickable(){
        onView(withId(R.id.stores_view)).perform(actionOnItemAtPosition<StoreItemViewHolder>(0,click()))
        onView(withId(R.id.secondFragment)).check(matches(isDisplayed()))
    }
}