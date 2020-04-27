package com.example.simplegithubsearch.ui

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.viewpager2.widget.ViewPager2
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun withBackgroundColor(@ColorRes colorRes: Int): Matcher<View> {
  return object : BoundedMatcher<View, View>(View::class.java) {
    override fun describeTo(description: Description?) {

    }

    override fun matchesSafely(item: View?): Boolean {
      if (item == null) return false

      if (!item.isShown) return false

      val bgColor = item.context.getColor(colorRes)

      val actualColor = try {
        (item.background as ColorDrawable).color
      } catch (e: Exception) {
        (item.background as GradientDrawable).color!!.defaultColor
      }
      return actualColor == bgColor
    }
  }
}

fun isCurrentPage(index: Int): Matcher<View> {
  return object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {

    }

    override fun matchesSafely(item: View?): Boolean {
      if (item == null) return false
      if (item !is ViewPager2) return false

      return item.currentItem == index
    }
  }
}