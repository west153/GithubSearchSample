package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.utils.concatMapZip
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
  private val userDetail1 = UserDetail("user1", "test1", "test1", "test1")
  private val userDetail2 = UserDetail("user2", "test2", "test2", "test2")

  @Mock
  private lateinit var remote: SearchDataSource

  @Before
  fun setup() {
    given(remote.userSearch("user")).willReturn(Flowable.just(arrayListOf(user1, user2)))
  }

  @Test
  fun userSearch_hasUser() {
    //given

    //when
    val userList = remote.userSearch("user")

    //then
    userList.test()
      .assertValue { it.size == 2 }
  }

  @Test
  fun userSearch_notFound() {
    //given
    given(remote.userSearch(ArgumentMatchers.anyString())).willReturn(Flowable.just(arrayListOf()))

    //when
    val userList = remote.userSearch("user")

    //then
    userList.test()
      .assertValue { it.isEmpty() }
  }

  @Test
  fun userSearch_convertToDetail() {
    //given
    given(remote.getUserDetail("user1")).willReturn(Flowable.just(userDetail1))
    given(remote.getUserDetail("user2")).willReturn(Flowable.just(userDetail2))

    //when
    val userDetailList = remote.userSearch("user")
      .map { users -> users.map { it.login } }
      .map { it.map { login -> remote.getUserDetail(login) } }
      .concatMapZip { it }

    //then
    userDetailList.test()
      .assertValue { it[0].bio == "test1" && it[1].bio == "test2" }
  }

}