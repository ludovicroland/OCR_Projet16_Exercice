package com.openclassrooms.arista.ui


import androidx.test.espresso.Espresso.onView
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
}
