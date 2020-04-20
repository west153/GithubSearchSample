package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.data.UserDetail
import io.reactivex.Flowable

interface SearchDataSource {
  fun userSearch(user: String): Flowable<List<User>>
  fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<User>>
  fun getUserDetail(login: String): Flowable<UserDetail>
}
