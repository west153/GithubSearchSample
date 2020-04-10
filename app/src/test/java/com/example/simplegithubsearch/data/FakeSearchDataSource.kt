package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.remote.User
import io.reactivex.Flowable

class FakeSearchDataSource(private val isEmpty: Boolean = false) : SearchDataSource {

  override fun userSearch(user: String): Flowable<List<User>> {
    val userList = arrayListOf<User>()
    val loop = if (isEmpty) 0 else 30
    for (i in 0 until loop) {
      userList.add(User("$user${i + 1}", "fakeHtml${i + 1}", "fakeAvatar${i + 1}"))
    }

    return Flowable.just(userList)
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>> {
    val userList = arrayListOf<User>()

    for (i in 0 until per_page) {
      userList.add(User("$user${i + 1}", "fakeHtml${i + 1}", "fakeAvatar${i + 1}"))
    }

    return Flowable.just(userList)
  }
}