package com.openclassrooms.arista.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.openclassrooms.arista.R

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExerciseFragmentTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewOfExerciseFragment() {
        // Clique sur le bouton Exercise dans la BottomNavigation
        onView(withId(R.id.nav_exercise)).perform(click())
        // Vérifiez que le RecyclerView affiche le bon nombre d'éléments
        onView(withId(R.id.exercise_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.exercise_recyclerview)).check(matches(hasChildCount(0)))
    }





}
