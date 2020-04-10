package com.example.simplegithubsearch.data

import com.example.simplegithubsearch.data.remote.User
import io.reactivex.Flowable

interface SearchDataSource {
  fun userSearch(user: String): Flowable<List<User>>
  fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>>
}