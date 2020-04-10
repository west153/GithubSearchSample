package com.example.simplegithubsearch.data

import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {
  private lateinit var fakeSearchDataSource: SearchDataSource
  private lateinit var searchRepository: SearchRepository

  @Before
  fun createRepository() {
    fakeSearchDataSource = FakeSearchDataSource()
    searchRepository = SearchRepositoryImpl(fakeSearchDataSource)
  }

  @Test
  fun userSearch_empty_result() {
    //given
    val emptyDataSource = FakeSearchDataSource(true)
    val searchRepository = SearchRepositoryImpl(emptyDataSource)

    //when
    val userList = searchRepository.userSearch("empty")

    //then
    userList.test().assertValue { it.isEmpty() }
  }

  @Test
  fun userSearch_default() {
    //given

    //when
    val userList = searchRepository.userSearch("user")

    //then
    userList.test().assertValue { it.size == 30 }
  }

  @Test
  fun userSearch_Paging_FiveUsers() {
    //given

    //when
    val userList = searchRepository.userSearch("pagingUser", 1, 5)

    //then
    userList.test().assertValue { it.size == 5 }
  }

}