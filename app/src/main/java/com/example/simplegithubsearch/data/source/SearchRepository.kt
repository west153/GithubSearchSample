package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.User
import io.reactivex.Flowable

interface SearchRepository {
  fun userSearch(user: String): Flowable<List<User>>
  fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>>
}