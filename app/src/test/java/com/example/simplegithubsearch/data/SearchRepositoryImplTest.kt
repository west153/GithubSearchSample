package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

  private val user1 = User("user1")
  private val user2 = User("user2")
  private val userList = arrayListOf(user1, user2)

  private lateinit var repository: SearchRepository

  @Mock
  private lateinit var remote: SearchDataSource

  @Before
  fun setup() {
    repository = SearchRepositoryImpl(remote)
    given(remote.userSearch("user")).willReturn(Flowable.just(userList))
  }

  @Test
  fun userSearch_convertToDetail() {
    //given
    val userDetail1 = UserDetail("user1", "test1", "test1", "test1")
    val userDetail2 = UserDetail("user2", "test2", "test2", "test2")
    given(remote.getUserDetail("user1")).willReturn(Flowable.just(userDetail1))
    given(remote.getUserDetail("user2")).willReturn(Flowable.just(userDetail2))

    //when
    val userDetailList = repository.userSearch("user")

    //then
    userDetailList.test()
      .assertValue { userDetail1.bio == "test1" && userDetail2.bio == "test2" }
  }

  @Test
  fun userSearch_NotFoundUsers() {
    //given
    given(remote.userSearch(ArgumentMatchers.anyString())).willReturn(Flowable.just(arrayListOf()))

    //when
    val userList = repository.userSearch("abc")

    //then
    userList.test()
      .assertValue { it.isEmpty() }
  }

}