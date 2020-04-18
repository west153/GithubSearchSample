package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.data.source.remote.ApiService
import io.reactivex.Flowable

class RemoteSearchDataSource(private val api: ApiService) : SearchDataSource {
  override fun userSearch(user: String): Flowable<List<User>> {
    return api.searchUser(user = user).map { it.items }
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>> {
    return api.searchUser(user = user, page = page, per_page = per_page).map { it.items }
  }
}