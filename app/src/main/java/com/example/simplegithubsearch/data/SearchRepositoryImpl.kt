package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.remote.User
import io.reactivex.Flowable

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {
  override fun userSearch(user: String): Flowable<List<User>> {
    return dataSource.userSearch(user)
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>> {
    return dataSource.userSearch(user, page, per_page)
  }
}