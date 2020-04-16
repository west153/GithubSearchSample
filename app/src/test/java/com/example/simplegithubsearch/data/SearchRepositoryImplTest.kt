package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {
  private lateinit var fakeSearchDataSource: SearchDataSource
  private lateinit var searchRepository: SearchRepository

  @Before
  fun createRepository() {
    fakeSearchDataSource = FakeSearchDataSource()
    searchRepository =
      SearchRepositoryImpl(
        fakeSearchDataSource
      )
  }

  @Test
  fun userSearch_empty_result() {
    //given

    //when
    val userList = searchRepository.userSearch("empty")

    //then
    userList.test().assertValue { it.isEmpty() }
  }

  @Test
  fun userSearch_default() {
    //given
    val users = arrayListOf<User>()

    for (i in 0 until 100) {
      if (i % 2 == 0)
        users.add(User("user", "", ""))
      else
        users.add(User("test", "", ""))
    }

    val testDataSource = FakeSearchDataSource(users)
    val testRepository: SearchRepository =
      SearchRepositoryImpl(
        testDataSource
      )

    //when
    val userList = testRepository.userSearch("user")

    //then
    userList.test().assertValue { it.size == 50 }
  }

  @Test
  fun userSearch_Paging_FiveUsers() {
    //given
    val users = arrayListOf<User>()

    for (i in 0 until 100) {
      users.add(User("user${i + 1}", "", ""))
    }

    val testDataSource = FakeSearchDataSource(users)
    val testRepository: SearchRepository =
      SearchRepositoryImpl(
        testDataSource
      )

    //when
    val userList = testRepository.userSearch("user", 1, 5)

    //then
    userList.test().assertValue { it.size == 5 }
  }

}