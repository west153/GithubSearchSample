package com.example.simplegithubsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.data.source.SearchRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchViewModel(private val searchRepository: SearchRepository) {
  private val unit get() = Event(Unit)
  private val compositeDisposable = CompositeDisposable()

  private val _doOnSubscribe = MutableLiveData<Event<Unit>>()
  val doOnSubscribe: LiveData<Event<Unit>> get() = _doOnSubscribe

  private val _userList = MutableLiveData<List<UserDetail>>()
  val userList: LiveData<List<UserDetail>> get() = _userList

  fun getUser(user: String) {
    val oldList = _userList.value ?: arrayListOf()

    searchRepository.userSearch(user)
      .doOnSubscribe {
        _userList.value = arrayListOf()
        _doOnSubscribe.value = unit
      }
      .subscribe(
        {
          _userList.value = oldList.diffToAdd(it)
        },
        { it.printStackTrace() }
      )
      .addTo(compositeDisposable)
  }

  fun clear() {
    compositeDisposable.clear()
  }

  private fun List<UserDetail>.diffToAdd(newList: List<UserDetail>): List<UserDetail> {
    val result = this.toMutableList()
    for (item in newList) {
      val any = this.any { it.login == item.login && it == item }
      if (!any) {
        result.add(item)
      }
    }
    return result
  }

}