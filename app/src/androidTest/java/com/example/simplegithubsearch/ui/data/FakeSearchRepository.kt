package com.example.simplegithubsearch.ui.data

import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.utils.concatMapZip
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer

class FakeSearchRepository(private val userList: ArrayList<User> = arrayListOf()) :
  SearchRepository {

  override fun userSearch(user: String): Flowable<List<UserDetail>> {
    return if (userList.isEmpty())
      Flowable.just(arrayListOf())
    else
      Flowable.just(userList)
        .compose(applyDetail())
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<UserDetail>> {
    return if (userList.isEmpty())
      Flowable.just(arrayListOf())
    else
      Flowable.just(userList)
        .compose(applyDetail())
  }

  private fun applyDetail(): FlowableTransformer<List<User>, List<UserDetail>> {
    return FlowableTransformer { observable ->
      observable.map { users -> users.map { it.login } }
        .map { login -> login.map { userToDetail(it) } }
        .concatMapZip { it }
    }
  }

  private fun userToDetail(login: String): Flowable<UserDetail> {
    return Flowable.just(UserDetail(login, "", "", ""))
  }
}