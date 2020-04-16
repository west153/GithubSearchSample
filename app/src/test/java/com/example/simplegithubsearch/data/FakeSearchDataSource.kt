package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.source.SearchDataSource
import io.reactivex.Flowable

class FakeSearchDataSource(private val data: MutableList<User> = arrayListOf()) :
  SearchDataSource {

  override fun userSearch(user: String): Flowable<List<User>> {
    return Flowable.just(data.filterUser(user))
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>> {
    return Flowable.just(data.filterUser(user).take(per_page))
  }

  private fun List<User>.filterUser(user: String) = this.filter { it.login.contains(user) }
}