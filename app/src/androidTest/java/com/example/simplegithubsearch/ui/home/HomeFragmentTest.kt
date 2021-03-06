package com.example.simplegithubsearch.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.RepositoryLocator
import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.ui.data.FakeSearchRepository
import com.example.simplegithubsearch.ui.main.MainActivity
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class HomeFragmentTest {

  @Test
  fun displayUser_hasData() {
    //given
    val inputText = "a"
    val users = arrayListOf(User("a1"))
    RepositoryLocator.searchRepository = FakeSearchRepository(users)

    launchActivity()

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))
    onView(withId(R.id.buttonSearch)).perform(ViewActions.click())

    //then
    onView(withText("a1")).check(matches(isDisplayed()))
  }

  @Test
  fun displayUser_noData() {
    //given
    val inputText = "a22"
    RepositoryLocator.searchRepository = FakeSearchRepository()
    launchActivity()

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))
    onView(withId(R.id.buttonSearch)).perform(ViewActions.click())

    //then
    onView(withText("a22")).check(doesNotExist())
  }


  @Test
  fun displayProgress_beforeUserSearching() {
    //given
    val inputText = "test"
    launchActivity()

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))
    onView(withId(R.id.buttonSearch)).perform(ViewActions.click())

    //then
    onView(withId(R.id.progress)).check(matches(isDisplayed()))
  }

  @Test
  fun displayNoDataView() {
    //given
    val inputText = "test"
    RepositoryLocator.searchRepository = FakeSearchRepository()
    launchActivity()

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))
    onView(withId(R.id.buttonSearch)).perform(ViewActions.click())

    //then
    onView(withId(R.id.noDataWrapper)).check(matches(isDisplayed()))
    onView(withId(R.id.userList)).check(matches(not(isDisplayed())))
  }

  private fun launchActivity(): ActivityScenario<MainActivity>? {
    return ActivityScenario.launch(MainActivity::class.java)
  }

}