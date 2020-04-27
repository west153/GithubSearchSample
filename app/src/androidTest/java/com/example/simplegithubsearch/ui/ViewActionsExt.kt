package com.example.simplegithubsearch.ui

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun selectTabPosition(index: Int): ViewAction {
  return object : ViewAction {
    override fun getDescription(): String = "selected tab $index"

    override fun getConstraints(): Matcher<View> =
      allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

    override fun perform(uiController: UiController?, view: View?) {
      val tabLayout = view as TabLayout

      val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(index)
        ?: throw PerformException.Builder()
          .withCause(Throwable("No tab at index $index"))
          .build()

      tabAtIndex.select()
    }
  }
}