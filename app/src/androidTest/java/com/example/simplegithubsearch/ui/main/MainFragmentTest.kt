package com.example.simplegithubsearch.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.ui.isCurrentPage
import com.example.simplegithubsearch.ui.selectTabPosition
import com.example.simplegithubsearch.ui.withBackgroundColor
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MainFragmentTest {

  @get:Rule
  var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

  @Test
  fun displayEditTextHint_whenNoInputText() {
    //given
    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(""))

    //then
    onView(
      allOf(withId(R.id.input), withHint(R.string.input_hint))
    ).check(matches(isDisplayed()))
  }

  @Test
  fun displayInputText() {
    //given
    val inputText = "1234"
    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))

    //then
    onView(withId(R.id.input)).check(matches(withText(inputText)))
  }

  @Test
  fun selectHomeTab() {
    //given
    onView(withId(R.id.tab)).perform(selectTabPosition(1))

    //when
    onView(withId(R.id.tab)).perform(selectTabPosition(0))

    //then
    onView(withId(R.id.userList)).check(matches(isDisplayed()))
    onView(withId(R.id.pager)).check(matches(isCurrentPage(0)))
  }

  @Test
  fun selectFavoriteTab() {
    //given

    //when
    onView(withId(R.id.tab)).perform(selectTabPosition(1))

    //then
    onView(withId(R.id.favorite)).check(matches(withBackgroundColor(R.color.test_color)))
    onView(withId(R.id.pager)).check(matches(isCurrentPage(1)))
  }

  @Test
  fun selectHomeTab_AfterClickSearchButton() {
    //given
    onView(withId(R.id.tab)).perform(selectTabPosition(1))

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText("test"))
    onView(withId(R.id.buttonSearch)).perform(click())

    //then
    onView(withId(R.id.userList)).check(matches(isDisplayed()))
    onView(withId(R.id.pager)).check(matches(isCurrentPage(0)))
  }

}