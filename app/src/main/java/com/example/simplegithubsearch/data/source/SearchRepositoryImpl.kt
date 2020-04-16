package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.User
import io.reactivex.Flowable

class SearchRepositoryImpl(private val remote: SearchDataSource) : SearchRepository {
  override fun userSearch(user: String): Flowable<List<User>> {
    return remote.userSearch(user)
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>> {
    return remote.userSearch(user, page, per_page)
  }
}