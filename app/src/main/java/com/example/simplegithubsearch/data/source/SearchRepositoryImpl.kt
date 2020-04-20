package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.utils.concatMapZip
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepositoryImpl(private val remote: SearchDataSource) : SearchRepository {
  override fun userSearch(user: String): Flowable<List<UserDetail>> {
    return remote.userSearch(user)
      .map { userList -> userList.map { it.login } }
      .concatMapZip { userList ->
        userList.map {
          remote.getUserDetail(it).subscribeOn(Schedulers.io())
        }
      }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<UserDetail>> {
    return remote.userSearch(user, page, per_page)
      .map { userList -> userList.map { it.login } }
      .concatMapZip { userList ->
        userList.map { remote.getUserDetail(it).subscribeOn(Schedulers.io()) }
      }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}