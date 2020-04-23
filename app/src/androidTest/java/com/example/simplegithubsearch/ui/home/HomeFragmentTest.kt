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
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.ui.data.FakeSearchRepository
import com.example.simplegithubsearch.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class HomeFragmentTest {

  private lateinit var searchRepository: SearchRepository

  @Before
  fun setup() {
    searchRepository = FakeSearchRepository()
    RepositoryLocator.searchRepository = searchRepository
  }

  @After
  fun clear() {
    RepositoryLocator.searchRepository = null
  }

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
    launchActivity()

    //when
    onView(withId(R.id.input)).perform(ViewActions.typeText(inputText))
    onView(withId(R.id.buttonSearch)).perform(ViewActions.click())

    //then
    onView(withText("a22")).check(doesNotExist())
  }

  @Test
  fun displayUser_listSize_30() {

  }

  @Test
  fun displayProgress_beforeUserSearching() {

  }

  @Test
  fun displayNoDataView() {

  }

  private fun launchActivity(): ActivityScenario<MainActivity>? {
    val activityScenario = ActivityScenario.launch(MainActivity::class.java)
    return activityScenario
  }

}