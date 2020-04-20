package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.UserDetail
import io.reactivex.Flowable

interface SearchRepository {
  fun userSearch(user: String): Flowable<List<UserDetail>>
  fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<UserDetail>>
}