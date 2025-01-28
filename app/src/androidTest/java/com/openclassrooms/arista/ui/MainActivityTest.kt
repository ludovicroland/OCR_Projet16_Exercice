package com.openclassrooms.arista.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.openclassrooms.arista.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest
{

  @Rule
  @JvmField
  var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

  @Test
  fun mainActivityTest()
  {
    val editText = onView(
      allOf(
        withId(R.id.et_name),
        isDisplayed()
      )
    )
    editText.check(matches(isDisplayed()))

    val editText2 = onView(
      allOf(
        withId(R.id.et_email),
        isDisplayed()
      )
    )
    editText2.check(matches(isDisplayed()))
  }



  @Test
  fun testInitialFragmentDisplayed() {
    // Vérifie que le fragment UserDataFragment est affiché au démarrage
    onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    onView(withId(R.id.et_name)).check(matches(isDisplayed()))
    onView(withId(R.id.et_email)).check(matches(isDisplayed()))
  }

  @Test
  fun testBottomNavigationNavigatesToExerciseFragment() {
    // Clique sur le bouton Exercise dans la BottomNavigation
    onView(withId(R.id.nav_exercise)).perform(click())

    // Vérifie que le fragment ExerciseFragment est affiché
    onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    onView(withId(R.id.exercise_recyclerview)).check(matches(isDisplayed()))
  }

  @Test
  fun testBottomNavigationNavigatesToSleepFragment() {
    // Clique sur le bouton Sleep dans la BottomNavigation
    onView(withId(R.id.nav_sleep)).perform(click())

    // Vérifie que le fragment SleepFragment est affiché
    onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    onView(withId(R.id.sleep_recyclerview)).check(matches(isDisplayed())) // Exemple d'élément spécifique au fragment Sleep
  }



}
