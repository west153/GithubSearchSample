package com.example.simplegithubsearch.data.source

import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.utils.concatMapZip
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepositoryImpl(private val remote: SearchDataSource) : SearchRepository {
  override fun userSearch(user: String): Flowable<List<UserDetail>> {
    return remote.userSearch(user)
      .compose(applyDetailSchedulers())
  }

  override fun userSearch(user: String, page: Int, per_page: Int): Flowable<List<UserDetail>> {
    return remote.userSearch(user, page, per_page)
      .compose(applyDetailSchedulers())
  }

  private fun applyDetailSchedulers(): FlowableTransformer<List<User>, List<UserDetail>> {
    return FlowableTransformer { observable ->
      observable.map { users -> users.map { it.login } }
        .map { login -> login.map { remote.getUserDetail(it).subscribeOn(Schedulers.io()) } }
        .concatMapZip { it }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    }
  }
}